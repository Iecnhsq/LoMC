package controller;

import entity.User;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.RegistrationServiceInterface;

@Controller
public class RegistrationController {

    private static final Logger LOGGER = Logger.getLogger(RegistrationController.class);

    @Resource(name = "RegistrationServiceInterface")
    private RegistrationServiceInterface registrationServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping(value = "/register.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("register");
            String login = request.getParameter("login");
            if (!registrationServiceInterface.loginValid(login)) {
                LOGGER.info("Login not valid!");
                return model;
            } else {
                User u = registrationServiceInterface.getUserInDB(login);
                if (registrationServiceInterface.userExist(u)) {
                    LOGGER.info("User is not exist in DB!");
                    return model;
                } else {
                    String password = request.getParameter("password");
                    String password2 = request.getParameter("password2");
                    if (!registrationServiceInterface.passwordValid(password, password2)) {
                        LOGGER.info("Password not valid!");
                        return model;
                    } else {
                        String city = request.getParameter("city");
                        if (!registrationServiceInterface.cityValid(city)) {
                            LOGGER.info("City not valid!");
                            return model;
                        } else {
                            String phone = request.getParameter("phone");
                            if (!registrationServiceInterface.phoneValid(phone)) {
                                LOGGER.info("Phone not valid!");
                                return model;
                            } else {
                                String email = request.getParameter("email");
                                if (!registrationServiceInterface.emailValid(email)) {
                                    LOGGER.info("Email not valid!");
                                    return model;
                                } else {
                                    registrationServiceInterface.registerUserInDb(login, password, city, phone, email);
                                    registrationServiceInterface.sendMailRegistrationInformation(login, password, email);
                                    registrationServiceInterface.sendRedirectRegistersussefuly(response);
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
