package controller;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.AccountService;
import service.CommonService;

@Controller
public class AccountContreller {

    public static final Logger LOGGER = Logger.getLogger(AccountContreller.class);

    @Autowired
    private AccountService accountService;
    @Autowired
    private UserDAO udao;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/account.html")
    public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonService.sendRedirectLoginNullInSesion(response);
        } else {
            User user = udao.getUserByLogin(login);
            String phone = request.getParameter("phone");
            if (phone == null) {
                ModelAndView model = new ModelAndView("account");
                model.addObject("isLogin", true);
                model.addObject("login", login);
                model.addObject("u", user);
                return model;
            } else {
                String email = request.getParameter("email");
                String city = request.getParameter("city");
                String pass = request.getParameter("pass");
                String pass2 = request.getParameter("pass2");
                if (!pass.equals(pass2)) {
                    ModelAndView model = new ModelAndView("account");
                    model.addObject("isLogin", true);
                    model.addObject("login", login);
                    model.addObject("u", user);
                    return model;
                } else {
                    try {
                        if (city.length() > 0) {
                            user.setCity(city);
                        }
                        if (email.length() > 0) {
                            user.setEmail(email);
                        }
                        if (phone.length() > 0) {
                            user.setPhone(phone);
                        }
                        String pass3 = request.getParameter("pass3");
                        if (pass3.equals(user.getPass())) {
                            if (pass.length() > 0) {
                                user.setPass(pass);
                            }
                            udao.updateUser(user);
                            response.sendRedirect("main.html");
                        } else {
                            ModelAndView model = new ModelAndView("account");
                            model.addObject("isLogin", true);
                            model.addObject("login", login);
                            model.addObject("u", user);
                            return model;
                        }
                    } catch (IOException ex) {
                        LOGGER.error("Error: " + ex);
                    }
                    return null;
                }
            }
        }
        return null;
    }

}
