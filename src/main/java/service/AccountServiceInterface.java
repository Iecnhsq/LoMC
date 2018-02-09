package service;

import entity.User;
import org.springframework.web.servlet.ModelAndView;

public interface AccountServiceInterface {

    public void modelAddObject(ModelAndView model, String login, User user);

}
