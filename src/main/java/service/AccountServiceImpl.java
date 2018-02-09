package service;

import entity.User;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service(value = "AccountServiceInterface")
public class AccountServiceImpl implements AccountServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(AccountServiceImpl.class);

    @Override
    public void modelAddObject(ModelAndView model, String login, User user) {
        model.addObject("isLogin", true);
        model.addObject("login", login);
        model.addObject("u", user);
    }

}
