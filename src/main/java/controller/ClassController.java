package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ClassServiceInterface;
import service.CommonServiceInterface;

@Controller
public class ClassController {

    private static final Logger LOGGER = Logger.getLogger(ClassController.class);

    @Resource(name = "ClassServiceInterface")
    private ClassServiceInterface classServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/class.html")
    public ModelAndView classs(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("class");
            return model;
        }
        return null;
    }

}
