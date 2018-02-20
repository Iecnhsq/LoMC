package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.IndexServiceInterface;

@Controller
public class IndexController {

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @Resource(name = "IndexServiceInterface")
    private IndexServiceInterface indexServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/index.html")
    public ModelAndView index(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("index");
            return model;
        }
        return null;
    }

}
