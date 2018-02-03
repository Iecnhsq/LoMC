package controller;

import dao.UserDAO;
import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonService;
import service.MainService;

@Controller
public class MainController {

    public static final Logger LOGGER = Logger.getLogger(MainController.class);
    private static final String ADMLOGIN = "admin";

    @Autowired
    private UserDAO udao;
    @Autowired
    private MainService mainService;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/main.html")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonService.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("main");
            boolean isLogin = false;
            boolean isAdmP = false;
            User u = udao.getUserByLogin(login);
            String exit = request.getParameter("exit");
            if (exit != null) {
                udao.updateUser(u);
                request.getSession().removeAttribute("login");
                mainService.sendRedirectUserLogout(response);
            } else {
                isLogin = true;
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
