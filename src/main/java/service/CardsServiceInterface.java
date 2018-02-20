package service;

import entity.Card;
import entity.User;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface CardsServiceInterface {

    public void modelAddObject(ModelAndView model, User u);

    public void fullDeck(HttpServletResponse response);

    public boolean classSelected(String idClass);

    public void addClassCardInSession(HttpServletRequest request, String heroClass);

    public void setEmptyDeck(String idClass);

    public boolean cardSelected(String idString);

    public Set<Card> writeCards(int id, Set<Card> cards);

    public boolean deckIsFull(Set<Card> cards);

    public void setDeck(Set<Card> cards);

    public boolean commitGetCard(String getCard);

    public List<Card> getCardsByLvl(List<Card> cards, int lvl);

    public Set<Card> getUserCards(ModelAndView model, String cardClassName);

}
