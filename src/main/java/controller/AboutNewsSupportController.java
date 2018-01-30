package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.AboutNewsSupportService;

@Controller
public class AboutNewsSupportController {

    public static final Logger LOGGER = Logger.getLogger(AboutNewsSupportController.class);

    @Autowired
    private AboutNewsSupportService aboutNewsSupportService;

    @RequestMapping("/about.html")
    public ModelAndView about(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            aboutNewsSupportService.sendRedirectLoginInSesion(response);
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
            aboutNewsSupportService.sendRedirectLoginInSesion(response);
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
            aboutNewsSupportService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("support");
            boolean isSendMess = false;
            String problem = request.getParameter("problem");         
            if (problem == null || problem.length() <= 10) {
                return new ModelAndView("support");
            } else {
                String subject = request.getParameter("subject");
                if (subject == null || subject.length() <= 10) {
                    return new ModelAndView("support");
                } else {
                    String email = request.getParameter("email");
                    if (email == null || email.length() <= 5) {
                        return new ModelAndView("support");
                    } else {
                        String message = request.getParameter("message");
                        if (message == null || (message.length() <= 25 && message.length() > 250)) {
                            return new ModelAndView("support");
                        } else {
                            aboutNewsSupportService.sendMail(problem, message, email, subject);
                            isSendMess = true;
                            aboutNewsSupportService.sendRedirectSendMessageSuccessfully(response);                           
                        }
                    }
                }
            }
            model.addObject("sendMess", isSendMess);
            return model;
        }
        return null;
    }

}
