package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.ADMServiceInterface;
import service.AddNewsServiceInterface;

@Controller
public class AddNewsController {
    
    private static final Logger LOGGER = Logger.getLogger(AddNewsController.class);

    @Resource(name = "ADMServiceInterface")
    private ADMServiceInterface aDMServiceInterface;
    @Resource(name = "AddNewsServiceInterface")
    private AddNewsServiceInterface addNewsServiceInterface;

    @RequestMapping(value = "/addNews.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView addNews(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (aDMServiceInterface.loginRequest(login)) {
            aDMServiceInterface.sendRedirectLoginNullInSesionOrNotAdmin(response);
        } else {
            ModelAndView model = new ModelAndView("addNews");
            String exit = request.getParameter("exit");
            if (exit != null) {
                aDMServiceInterface.sendRedirectAndRemoveAttributeLogin(request, response);
            } else {
                String subject = request.getParameter("subject");
                String author = login;
                String text = request.getParameter("text");
                if (subject != null && text != null) {
                    addNewsServiceInterface.addNewsInDb(subject, author, text);
                } else {
                    return model;
                }
            }
            addNewsServiceInterface.getNewsAndLogin(model, login);
            return model;
        }
        return null;
    }

}
