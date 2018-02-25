package service;

import entity.Card;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface MainServiceInterface {

    public void sendRedirectUserLogout(HttpServletResponse response);

    public Set<Card> getUserCards(ModelAndView model, String cardClassName);

}
