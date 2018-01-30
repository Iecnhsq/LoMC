package controller;

import dao.UserDAO;
//import entity.User;
import java.util.Map;
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
import org.springframework.web.servlet.ModelAndView;
import service.LoginService;
import validation.User;

@Controller
public class LoginController {
    
//    public static final Logger LOGGER = Logger.getLogger(LoginController.class);
//
//    @Autowired
//    private LoginService loginService;
//    @Autowired
//    private UserDAO udao;
//
//    @RequestMapping(value = "/login.html", method = {RequestMethod.GET, RequestMethod.POST})
//    public ModelAndView login(HttpServletRequest request, HttpServletResponse response) {
//        String loginInSesion = (String) request.getSession().getAttribute("login");
//        if (loginInSesion != null) {
//            loginService.sendRedirectLoginInSesion(response);
//        } else {
//            ModelAndView model = new ModelAndView("login");
//            String login = request.getParameter("login");
//            if (login != null && login.length() >= 4) {
//                String password = request.getParameter("password");
//                User u = udao.getUserByLogin(login);
//                if (u.getPass().equals(password)) {
//                    request.getSession().setAttribute("login", login);
//                    loginService.sendRedirectLoginInUser(response); 
//                } else {
//                    return new ModelAndView("login");
//                }
//            } else {
//                return new ModelAndView("login");
//            }
//            return model;
//        }
//        return null;
//    }
    
    
 
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String viewLogin(Map<String, Object> model) {
        User user = new User();
        model.put("userForm", user);
        return "LoginForm";
    }
 
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String doLogin(@Valid @ModelAttribute("userForm") User userForm,
            BindingResult result, Map<String, Object> model) {
 
        if (result.hasErrors()) {
            return "LoginForm";
        }
 
        return "LoginSuccess";
    }

}
