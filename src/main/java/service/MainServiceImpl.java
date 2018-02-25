package service;

import com.google.gson.Gson;
import entity.Card;
import entity.Deck;
import entity.User;
import holders.CardHolder;
import holders.UserHolder;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service(value = "MainServiceInterface")
public class MainServiceImpl implements MainServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(MainServiceImpl.class);

    @Autowired
    private CardHolder ch;
    @Autowired
    private UserHolder uh;

    @Override
    public Set<Card> getUserCards(ModelAndView model, String cardClassName) {
        User u = uh.getUser();
        Set<Card> cards = new LinkedHashSet<>();
        String uCard = u.getCards();
        System.out.println(uCard);
        Deck deck = new Gson().fromJson(uCard, Deck.class);
        deck.deck.forEach((i) -> {
            cards.add(ch.getCardById(i));
        });
        model.addObject("card", cards);
        model.addObject("cards", ch.getCardByClass("BasicCard"));
        return cards;
    }

    @Override
    public void sendRedirectUserLogout(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            LOGGER.info("User Logout! Send redirect to index!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
