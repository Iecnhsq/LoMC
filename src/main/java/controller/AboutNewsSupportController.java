package controller;

import entity.Support;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.AboutNewsSupportServiceInterface;
import service.CommonServiceInterface;

@Controller
public class AboutNewsSupportController {

    private static final Logger LOGGER = Logger.getLogger(AboutNewsSupportController.class);

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

    @RequestMapping(value = "/support", method = RequestMethod.GET)
    public String viewSupport(Map<String, Object> model) {
        Support support = new Support();
        model.put("supportForm", support);
        return "support";
    }

    @RequestMapping(value = "/support", method = RequestMethod.POST)
    public String addSupport(@Valid @ModelAttribute("supportForm") Support supportForm, BindingResult result, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            if (result.hasErrors()) {
                return "support";
            } else {
                String problem = request.getParameter("problem");
                String subject = request.getParameter("subject");
                String email = request.getParameter("email");
                String message = request.getParameter("message");
                aboutNewsSupportServiceInterface.addSupportLetterInDb(problem, subject, message, email);
                aboutNewsSupportServiceInterface.sendMail(problem, message, email, subject);
                aboutNewsSupportServiceInterface.sendRedirectSendMessageSuccessfully(response);
            }
        }
        return null;
    }

}
