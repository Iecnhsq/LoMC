package controller;

import dao.UserDAO;
import entity.User;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.RecoveryService;

@Controller
public class RecoveryController {

    private static final Logger LOGGER = Logger.getLogger(RecoveryController.class);
    private final String ca = captcha();

    @Autowired
    private RecoveryService recoveryService;
    @Autowired
    private UserDAO udao;

    @RequestMapping(value = "/recovery.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView recovery(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            recoveryService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("recovery");
            String login = request.getParameter("login");
            if (login == null || login.length() < 4) {
                return new ModelAndView("recovery");
            } else {
                User u = udao.getUserByLogin(login);
                String email = request.getParameter("email");
                if (email == null || email.length() < 5 || !u.getEmail().equals(email)) {
                    return new ModelAndView("recovery");
                } else {
                    recoveryService.sendMailRecoveryKey(ca, email);
                    request.getSession().setAttribute("email", email);
                    request.getSession().setAttribute("password", u.getPass());
                    request.getSession().setAttribute("ca", ca);
                    recoveryService.sendRedirectSendRecoveryMail(response);
                }
            }
            return model;
        }
        return null;
    }

    private String captcha() {
        String s = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz1234567890";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            sb.append(s.charAt(new Random().nextInt(s.length())));
        }
        return sb.toString();
    }

}
