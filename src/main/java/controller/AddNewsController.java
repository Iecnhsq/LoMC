package controller;

import dao.NewsDAO;
import entity.News;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AddNewsController {

    @Autowired
    private NewsDAO ndao;

    @RequestMapping(value = "/addNews.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView admTools(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null || !login.equals("admin")) {
            try {
                response.sendRedirect("index.html");
            } catch (IOException ex) {
                Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ModelAndView model = new ModelAndView("addNews");
            String exit = request.getParameter("exit");
            if (exit != null) {
                try {
                    request.getSession().removeAttribute("login");
                    response.sendRedirect("index.html");
                } catch (IOException ex) {
                    Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String subject = request.getParameter("subject");
                String author = login;
                String text = request.getParameter("text");
                if (subject != null && text != null) {
                    News news = new News(new Random().nextInt(), subject, author, new Date(), text);
                } else {
                    return model;
                }
            }
            List<News> allnews = ndao.getNews();
            model.addObject("allnews", allnews);
            model.addObject("login", login);
            return model;
        }
        return null;
    }

}
