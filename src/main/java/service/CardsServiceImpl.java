package service;

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
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service(value = "CardsServiceInterface")
public class CardsServiceImpl implements CardsServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(CardsServiceImpl.class);

    @Autowired
    private UserHolder uh;
    @Autowired
    private CardHolder ch;

    @Override
    public void modelAddObject(ModelAndView model, User u) {
        model.addObject("user", u);
        model.addObject("classs", u.getClasss());
        model.addObject("lvl", u.getLvl());
        model.addObject("pts", u.getPoints());
        model.addObject("mon", u.getMoney());
        model.addObject("rDate", u.getDate());
    }

    @Override
    public void fullDeck(HttpServletResponse response) {
        try {
            response.sendRedirect("card.html");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    private Card addCard(Integer idInt) {
        return ch.getCardById(idInt);
    }

    private void removeCard(Set<Card> cards, Integer idInt) {
        for (Card c : cards) {
            if (c.getId() == (-idInt)) {
                cards.remove(c);
                break;
            }
        }
    }

    @Override
    public List<Card> getCardsByLvl(List<Card> cards, int lvl) {
        List<Card> levelList = new LinkedList<>();
        cards.forEach((c) -> {
            if (c.getLevel() == lvl) {
                levelList.add(c);
            }
        });
        return levelList;
    }

    @Override
    public boolean commitGetCard(String getCard) {
        return getCard != null;
    }

    @Override
    public Set<Card> writeCards(int id, Set<Card> cards) {
        if (id == 0) {
            cards.clear();
        } else if (id > 0) {
            cards.add(addCard(id));
            System.out.println(cards.size());
        } else if (id < 0) {
            removeCard(cards, id);
            System.out.println(cards.size());
        }
        return cards;
    }

    @Override
    public boolean deckIsFull(Set<Card> cards) {
        return cards.size() > 10;
    }

    @Override
    public boolean cardSelected(String idString) {
        return idString != null;
    }

    @Override
    public void addClassCardInSession(HttpServletRequest request, String heroClass) {
        request.getSession().setAttribute("heroClass", heroClass);
        request.getSession().setAttribute("cardClass", ch.getCardByClass(heroClass));

    }

    @Override
    public void setEmptyDeck(String idClass) {
        Deck d = new Deck();
        User u = uh.getUser();
        u.setCards(new Gson().toJson(d));
        u.setClasss(idClass);
        uh.set(u);
    }

    @Override
    public boolean classSelected(String idClass) {
        return idClass != null;
    }

    @Override
    public void setDeck(Set<Card> cards) {
        Deck d = new Deck();
        System.out.println(cards.size());
        cards.forEach((c) -> {
            d.deck.add(c.getId());
            d.deck.size();
        });
        String userCards = new Gson().toJson(d);
        System.out.println(userCards);
        uh.getUser().setCards(userCards);
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
