package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.WaitServiceInterface;

@Controller
public class WaitController {

    public static final Logger LOGGER = Logger.getLogger(WaitController.class);

    @Resource(name = "WaitServiceInterface")
    private WaitServiceInterface waitServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/wait.html")
    public ModelAndView wait(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("wait");
            return model;
        }
        return null;
    }

}
