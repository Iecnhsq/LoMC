package service;

import battle.Battle;
import com.google.gson.Gson;
import entity.Card;
import entity.Deck;
import entity.User;
import holders.CardHolder;
import holders.UserHolder;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service(value = "WaitServiceInterface")
public class WaitServiceImpl implements WaitServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(WaitServiceImpl.class);

    @Autowired
    private CardHolder ch;
    @Autowired
    private UserHolder uh;

    @Override
    public void cardsLesssThenTen(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    @Override
    public void getBattle(HttpServletResponse response) {
        try {
            response.sendRedirect("battle.html");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    @Override
    public void pairUsers(Map<String, User> inSearch) {
        if (!inSearch.isEmpty()) {
            List<String> paired = new LinkedList<>();
            while (inSearch.size() > 1) {
                inSearch.entrySet().stream().map((entry) -> {
                    paired.add(entry.getKey());
                    return entry;
                }).filter((_item) -> (paired.size() == 2)).map((_item) -> {
                    inSearch.remove(paired.get(0));
                    return _item;
                }).forEachOrdered((_item) -> {
                    inSearch.remove(paired.get(1));
                });
            }
        }
    }

    @Override
    public boolean inBattle(String login, Battle b) {
        return b.p1.getLogin().equals(login) || b.p2.getLogin().equals(login);
    }

    @Override
    public void modelAddObject(ModelAndView model, User u, String login) {
        model.addObject("classs", u.getClasss());
        model.addObject("lvl", u.getLvl());
        model.addObject("pts", u.getPoints());
        model.addObject("mon", u.getMoney());
        model.addObject("rDate", u.getDate());
        model.addObject("level", u.getLvl());
        model.addObject("login", login);
    }

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

}
