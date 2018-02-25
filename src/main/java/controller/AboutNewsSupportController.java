package controller;

import entity.Support;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.AboutNewsSupportServiceInterface;
import service.CommonServiceInterface;

@Controller
public class AboutNewsSupportController {

    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;
    @Resource(name = "AboutNewsSupportServiceInterface")
    private AboutNewsSupportServiceInterface aboutNewsSupportServiceInterface;

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String viewAbout(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            return "about";
        }
        return null;
    }

    @RequestMapping(value = "/news", method = RequestMethod.GET)
    public String viewNews(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            aboutNewsSupportServiceInterface.getNews(model);
            return "news";
        }
        return null;
    }

    @RequestMapping(value = "/support", method = RequestMethod.GET)
    public String viewSupport(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            Support support = new Support();
            model.put("supportForm", support);
            return "support";
        }
        return null;
    }

    @RequestMapping(value = "/support", method = RequestMethod.POST)
    public String addSupport(@Valid @ModelAttribute("supportForm") Support supportForm, BindingResult result, Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) {
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
        return null;
    }

}
