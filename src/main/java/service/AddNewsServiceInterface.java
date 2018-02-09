package service;

import org.springframework.web.servlet.ModelAndView;

public interface AddNewsServiceInterface {

    public void getNewsAndLogin(ModelAndView model, String login);

    public boolean addNewsInDb(String subject, String author, String text);

}
