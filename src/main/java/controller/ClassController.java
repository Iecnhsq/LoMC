package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.ClassService;
import service.CommonService;

@Controller
public class ClassController {

    public static final Logger LOGGER = Logger.getLogger(ClassController.class);

    @Autowired
    private ClassService classService;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/class.html")
    public ModelAndView classs(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonService.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("class");
            return model;
        }
        return null;
    }

}
