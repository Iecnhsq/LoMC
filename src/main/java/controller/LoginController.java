package controller;

import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CommonService;
import service.LoginService;

@Controller
public class LoginController {

    public static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Autowired
    private LoginService loginService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/login.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("login");
            String login = request.getParameter("login");
            if (!loginService.loginValid(login)) {
                LOGGER.info("Login is not valid!");
                return model;
            } else {
                User u = loginService.getUserInDB(login);
                if (!loginService.userExist(u)) {
                    LOGGER.info("User is exist in DB!");
                    return model;
                } else {
                    String password = request.getParameter("password");
                    if (!loginService.passwordValid(password, u)) {
                        LOGGER.info("Password is not valid!");
                        return model;
                    } else {
                        request.getSession().setAttribute("login", login);
                        loginService.sendRedirectLoginInUser(response);
                    }
                }
            }
        }
        return null;
    }

}
