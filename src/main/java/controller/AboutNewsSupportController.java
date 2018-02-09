package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.AboutNewsSupportServiceInterface;
import service.CommonServiceInterface;

@Controller
public class AboutNewsSupportController {

    public static final Logger LOGGER = Logger.getLogger(AboutNewsSupportController.class);

    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;
    @Resource(name = "AboutNewsSupportServiceInterface")
    private AboutNewsSupportServiceInterface aboutNewsSupportServiceInterface;

    @RequestMapping("/about.html")
    public ModelAndView about(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
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
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("news");
            aboutNewsSupportServiceInterface.getNews(model);
            return model;
        }
        return null;
    }

    @RequestMapping(value = "/support.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView support(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("support");
            String problem = request.getParameter("problem");
            if (problem == null || problem.length() < 5) {
                return model;
            } else {
                String subject = request.getParameter("subject");
                if (subject == null || subject.length() < 5) {
                    return model;
                } else {
                    String email = request.getParameter("email");
                    if (email == null || email.length() < 5) {
                        return model;
                    } else {
                        String message = request.getParameter("message");
                        if (message == null || message.length() < 10) {
                            return model;
                        } else {
                            aboutNewsSupportServiceInterface.addSupportLetterInDb(problem, subject, message, email);
                            aboutNewsSupportServiceInterface.sendMail(problem, message, email, subject);
                            aboutNewsSupportServiceInterface.sendRedirectSendMessageSuccessfully(response);
                        }
                    }
                }
            }
        }
        return null;
    }

}
