package service;

import battle.Battle;
import entity.Card;
import entity.User;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface WaitServiceInterface {

    public void cardsLesssThenTen(HttpServletResponse response);

    public void pairUsers(Map<String, User> inSearch);

    public boolean inBattle(String login, Battle b);

    public void getBattle(HttpServletResponse response);

    public void modelAddObject(ModelAndView model, User u, String login);

    public Set<Card> getUserCards(ModelAndView model, String cardClassName);

}
