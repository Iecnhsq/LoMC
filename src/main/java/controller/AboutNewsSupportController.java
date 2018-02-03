package controller;

import dao.SupportDAOImplementation;
import entity.Support;
import java.util.Date;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.AboutNewsSupportService;
import service.CommonService;

@Controller
public class AboutNewsSupportController {

    public static final Logger LOGGER = Logger.getLogger(AboutNewsSupportController.class);

    @Autowired
    private AboutNewsSupportService aboutNewsSupportService;
    @Autowired
    private CommonService commonService;
    @Autowired
    private SupportDAOImplementation supportDAOImplementation;

    @RequestMapping("/about.html")
    public ModelAndView about(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("about");
            return model;
        }
        return null;
    }

    @RequestMapping("/news.html")
    public ModelAndView news(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("news");
            aboutNewsSupportService.getNews(model);
            return model;
        }
        return null;
    }

    @RequestMapping(value = "/support.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView support(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("support");
            String problem = request.getParameter("problem");
            if (problem == null) {
                return model;
            } else {
                String subject = request.getParameter("subject");
                if (subject == null) {
                    return model;
                } else {
                    String email = request.getParameter("email");
                    if (email == null) {
                        return model;
                    } else {
                        String message = request.getParameter("message");
                        if (message == null) {
                            return model;
                        } else {
                            Support support = new Support(new Random().nextInt(), problem, subject, email, message, new Date());
                            supportDAOImplementation.addSuppotLetter(support);
                            aboutNewsSupportService.sendMail(problem, message, email, subject);
                            aboutNewsSupportService.sendRedirectSendMessageSuccessfully(response);
                        }
                    }
                }
            }
//            if (!aboutNewsSupportService.allParametrNull(problem, subject, email, message)) {
//                LOGGER.info("Method 'allParametrNull' is null!");
//                return model;
//            } else {
//                if (!aboutNewsSupportService.getProblem(problem) && !aboutNewsSupportService.getSubject(subject) && !aboutNewsSupportService.getEmail(email) && !aboutNewsSupportService.getMessage(message)) {
//                    LOGGER.info("Methods 'get' is not valid!");
//                    return model;
        }
        return null;
    }

}
