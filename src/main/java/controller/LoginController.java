package controller;

import entity.User;
import holders.UserHolder;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CommonServiceInterface;
import service.LoginServiceInterface;

@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);
    private static final String ISNOT = "User or password are not valid! Please, try again...";

    @Resource(name = "LoginServiceInterface")
    private LoginServiceInterface loginServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;
    @Autowired
    private UserHolder uh;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLogin(Map<String, Object> model) {
        User user = new User();
        model.put("userForm", user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("userForm") User userForm, BindingResult result, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            if (result.hasErrors()) {
                return "login";
            } else {
                String login = request.getParameter("login");
                User u = loginServiceInterface.getUserInDB(login);
                if (!loginServiceInterface.userExist(u)) {
                    LOGGER.info("User is exist in DB!");
                    model.put("ex", ISNOT);
                    return "login";
                } else {
                    String password = request.getParameter("pass");
                    if (!loginServiceInterface.passwordValid(password, u)) {
                        LOGGER.info("Password is not equals!");
                        model.put("ex", ISNOT);
                        return "login";
                    } else {
                        request.getSession().setAttribute("login", login);
                        uh.set(u);
                        loginServiceInterface.getAllCardsInDB();
                        loginServiceInterface.sendRedirectLoginInUser(response);
                    }
                }
            }
        }
        return null;
    }

}
