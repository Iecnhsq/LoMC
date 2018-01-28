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
import service.MainService;

@Controller
public class MainController {

    public static final Logger LOGGER = Logger.getLogger(MainController.class);

    @Autowired
    UserDAO udao;
    @Autowired
    MainService mainService;

    @RequestMapping("/main.html")
    public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            try {
                response.sendRedirect("index.html");
                System.out.println("Login is null! Send redirect to index!");
                LOGGER.info("Login is null! Send redirect to index!");
            } catch (IOException ex) {
                System.out.println("Error: " + ex);
            }
        } else {
            ModelAndView model = new ModelAndView("main");
            boolean isLogin = false;
            User u = udao.getUserByLogin(login);
            String exit = request.getParameter("exit");
            if (exit != null) {
                udao.updateUser(u);
                request.getSession().removeAttribute("login");
                //User Logout! Send redirect to index!
                mainService.sendRedirectUserLogout(response);
            } else {
                isLogin = true;
                model.addObject("user", u);
            }
            model.addObject("login", login);
            model.addObject("isLogin", isLogin);
            return model;
        }
        return null;
    }

}
