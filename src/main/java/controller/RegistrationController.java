package controller;

import dao.UserDAO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.RegistrationService;

@Controller
public class RegistrationController {

    @Autowired
    RegistrationService registrationService;
    @Autowired
    UserDAO udao;

    @RequestMapping(value = "/register.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            //Login not null! Send redirect to main!
            registrationService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("register");
            String login = request.getParameter("login");
            String password = request.getParameter("password");
            String password2 = request.getParameter("password2");
            String city = request.getParameter("city");
            String phone = request.getParameter("phone");
            String email = request.getParameter("email");
            return model;
        }
        return null;
    }

}
