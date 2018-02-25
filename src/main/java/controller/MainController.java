package controller;

import dao.UserDAOInterface;
import entity.User;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.MainServiceInterface;

@Controller
public class MainController {

    private static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final String ADMLOGIN = "admin";

    @Resource(name = "UserDAOInterface")
    private UserDAOInterface udao;
    @Resource(name = "MainServiceInterface")
    private MainServiceInterface mainServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/main.html")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("main");
            boolean isLogin = false;
            boolean isAdmP = false;
            User u = udao.getUserByLogin(login);
            String exit = request.getParameter("exit");
            if (exit != null) {
                udao.updateUser(u);
                request.getSession().removeAttribute("login");
                mainServiceInterface.sendRedirectUserLogout(response);
            } else {
                isLogin = true;
                mainServiceInterface.getUserCards(model, u.getClasss());
                model.addObject("user", u);
                if (login.equals(ADMLOGIN)) {
                    isAdmP = true;
                }
            }
            model.addObject("login", login);
            model.addObject("isLogin", isLogin);
            model.addObject("isAdmP", isAdmP);
            return model;
        }
        return null;
    }

}
