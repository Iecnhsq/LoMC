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
import service.RegistrationService;

@Controller
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Autowired
    private RegistrationService registrationService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/register.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("register");
            String login = request.getParameter("login");
            if (!registrationService.loginValid(login)) {
                LOGGER.info("Login not valid!");
                return model;
            } else {
                User u = registrationService.getUserInDB(login);
                if (registrationService.userExist(u)) {
                    LOGGER.info("User is not exist in DB!");
                    return model;
                } else {
                    String password = request.getParameter("password");
                    String password2 = request.getParameter("password2");
                    if (!registrationService.passwordValid(password, password2)) {
                        LOGGER.info("Password not valid!");
                        return model;
                    } else {
                        String city = request.getParameter("city");
                        if (!registrationService.cityValid(city)) {
                            LOGGER.info("City not valid!");
                            return model;
                        } else {
                            String phone = request.getParameter("phone");
                            if (!registrationService.phoneValid(phone)) {
                                LOGGER.info("Phone not valid!");
                                return model;
                            } else {
                                String email = request.getParameter("email");
                                if (!registrationService.emailValid(email)) {
                                    LOGGER.info("Email not valid!");
                                    return model;
                                } else {
                                    registrationService.registerUserInDb(login, password, city, phone, email);
                                    registrationService.sendMailRegistrationInformation(login, password, email);
                                    registrationService.sendRedirectRegistersussefuly(response);
                                }
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

}
