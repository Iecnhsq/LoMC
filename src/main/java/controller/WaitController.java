package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonService;
import service.WaitService;

@Controller
public class WaitController {

    public static final Logger LOGGER = Logger.getLogger(WaitController.class);

    @Autowired
    private WaitService waitService;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/wait.html")
    public ModelAndView wait(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonService.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("wait");
            return model;
        }
        return null;
    }

}
