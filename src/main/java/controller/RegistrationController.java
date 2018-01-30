package controller;

import com.google.gson.Gson;
import dao.UserDAO;
import entity.Deck;
import entity.User;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.RegistrationService;

@Controller
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private UserDAO udao;

    @RequestMapping(value = "/register.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            registrationService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("register");
            String login = request.getParameter("login");
            if (login == null || login.length() < 4) {
                return new ModelAndView("register");
            } else {
                User u = udao.getUserByLogin(login);
                String password = request.getParameter("password");
                String password2 = request.getParameter("password2");
                if (password == null || !password.equals(password2) || !u.getPass().equals(password)) {
                    return new ModelAndView("register");
                } else {
                    String city = request.getParameter("city");
                    if (city == null || city.length() < 3) {
                        return new ModelAndView("register");
                    } else {
                        String phone = request.getParameter("phone");
                        if (phone == null || phone.length() < 10) {
                            return new ModelAndView("register");
                        } else {
                            String email = request.getParameter("email");
                            if (email == null || email.length() < 5) {
                                return new ModelAndView("register");
                            } else {
                                User user = new User(new Random().nextInt(), login, password, new Date(), 0, 0, new Gson().toJson(new Deck()), "Mage", 0, city, phone, email, 'n');
                                udao.addUser(user);
                                registrationService.sendMailRegistrationInformation(login, password, email);
                                registrationService.sendRedirectRegistersussefuly(response);
                            }
                        }
                    }
                }
            }
            return model;
        }
        return null;
    }

}
