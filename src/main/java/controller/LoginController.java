package controller;

import entity.User;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.LoginServiceInterface;

@Controller
public class LoginController {

    public static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Resource(name = "LoginServiceInterface")
    private LoginServiceInterface loginServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLogin(Map<String, Object> model) {
        User user = new User();
        model.put("userForm", user);
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("userForm") User userForm,
            BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            return "login";
        }
        return "main";
    }

//    @RequestMapping(value = "/login.html", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
//        String loginInSesion = (String) request.getSession().getAttribute("login");
//        if (loginInSesion != null) {
//            commonServiceInterface.sendRedirectLoginInSesion(response);
//        } else {
//            ModelAndView model = new ModelAndView("login");
//            String login = request.getParameter("login");
//            if (!loginServiceInterface.loginValid(login)) {
//                LOGGER.info("Login is not valid!");
//                return model;
//            } else {
//                User u = loginServiceInterface.getUserInDB(login);
//                if (!loginServiceInterface.userExist(u)) {
//                    LOGGER.info("User is exist in DB!");
//                    return model;
//                } else {
//                    String password = request.getParameter("password");
//                    if (!loginServiceInterface.passwordValid(password, u)) {
//                        LOGGER.info("Password is not valid!");
//                        return model;
//                    } else {
//                        request.getSession().setAttribute("login", login);
//                        loginServiceInterface.sendRedirectLoginInUser(response);
//                    }
//                }
//            }
//        }
//        return null;
//    }
}
