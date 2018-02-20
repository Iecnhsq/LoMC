package controller;

import dao.UserDAOInterface;
import entity.User;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.AccountServiceInterface;
import service.CommonServiceInterface;

@Controller
public class AccountContreller {

    private static final Logger LOGGER = Logger.getLogger(AccountContreller.class);

    @Resource(name = "AccountServiceInterface")
    private AccountServiceInterface accountServiceInterface;
    @Resource(name = "UserDAOInterface")
    private UserDAOInterface udao;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/account.html")
    public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("account");
            User user = udao.getUserByLogin(login);
            String phone = request.getParameter("phone");
            if (phone == null) {
                accountServiceInterface.modelAddObject(model, login, user);
                return model;
            } else {
                String pass = request.getParameter("pass");
                String pass2 = request.getParameter("pass2");
                if (!pass.equals(pass2)) {
                    accountServiceInterface.modelAddObject(model, login, user);
                    return model;
                } else {
                    try {
                        String email = request.getParameter("email");
                        String city = request.getParameter("city");
                        if (city.length() > 0 || email.length() > 0 || phone.length() > 0) {
                            user.setCity(city);
                            user.setEmail(email);
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
                            accountServiceInterface.modelAddObject(model, login, user);
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
