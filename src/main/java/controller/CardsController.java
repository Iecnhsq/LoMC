package controller;

import dao.UserDAOInterface;
import entity.Card;
import entity.User;
import holders.CardHolder;
import holders.UserHolder;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CardsServiceInterface;
import service.CommonServiceInterface;

@Controller
public class CardsController {

    private static final Logger LOGGER = Logger.getLogger(CardsController.class);

    @Autowired
    private UserHolder uh;
    @Autowired
    private CardHolder ch;
    @Resource(name = "UserDAOInterface")
    private UserDAOInterface udao;
    @Resource(name = "CardsServiceInterface")
    private CardsServiceInterface cardsServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/cards.html")
    public ModelAndView cards(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("cards");
            User u = uh.getUser();
            List<Card> allCards = ch.getCardByClass("BasicCard");
            cardsServiceInterface.modelAddObject(model, u);
            String idString = request.getParameter("id");
            Set<Card> cards;
            String idClass = request.getParameter("idclass");
            String classNameJoin = u.getClasss();
            if (cardsServiceInterface.classSelected(idClass)) {
                cardsServiceInterface.addClassCardInSession(request, idClass);
                cardsServiceInterface.setEmptyDeck(idClass);
                udao.updateUser(u);
            } else {
                cardsServiceInterface.addClassCardInSession(request, classNameJoin);
            }
            String CardClassName = u.getClasss();
            cards = cardsServiceInterface.getUserCards(model, CardClassName);
            if (cardsServiceInterface.cardSelected(idString)) {
                LOGGER.info("ID: " + idString);
                int id = Integer.parseInt(idString);
                cards = cardsServiceInterface.writeCards(id, cards);
                if (cardsServiceInterface.deckIsFull(cards)) {
                    cardsServiceInterface.fullDeck(response);
                    return null;
                }
            }
            cardsServiceInterface.setDeck(cards);
            String getCards = request.getParameter("getCards");
            if (cardsServiceInterface.commitGetCard(getCards)) {
                udao.updateUser(u);
                commonServiceInterface.sendRedirectLoginInSesion(response);
                return null;
            }
            model.addObject("deckCards", cards);
            for (int j = 0; j <= 10; j++) {
                model.addObject("level" + j + "Cards", cardsServiceInterface.getCardsByLvl(allCards, j));
            }
            return model;
        }
        return null;
    }

}
