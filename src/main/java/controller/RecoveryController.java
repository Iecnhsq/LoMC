package controller;

import entity.User;
import java.util.Random;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.RecoveryServiceInterface;

@Controller
public class RecoveryController {

    private static final Logger LOGGER = Logger.getLogger(RecoveryController.class);
    private final String ca = captcha();

    @Resource(name = "RecoveryServiceInterface")
    private RecoveryServiceInterface recoveryServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping(value = "/recovery.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView recovery(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("recovery");
            String login = request.getParameter("login");
            User u = recoveryServiceInterface.getUserInDB(login);
            if (!recoveryServiceInterface.userExist(u)) {
                LOGGER.info("User is not exist!");
                return model;
            } else {
                String email = request.getParameter("email");
                if (!recoveryServiceInterface.loginValid(login, u) && !recoveryServiceInterface.emailValid(email, u)) {
                    LOGGER.info("Login or E-mail not valid!");
                    return model;
                } else {
                    recoveryServiceInterface.sendMailRecoveryKey(ca, email);
                    recoveryServiceInterface.setAttribute(request, email, u, ca);
                    recoveryServiceInterface.sendRedirectSendRecoveryMail(response);
                }
            }
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
