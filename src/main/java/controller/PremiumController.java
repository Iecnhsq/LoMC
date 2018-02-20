package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.PremiumServiceInterface;

@Controller
public class PremiumController {

    private static final Logger LOGGER = Logger.getLogger(PremiumController.class);

    @Resource(name = "PremiumServiceInterface")
    private PremiumServiceInterface premiumServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping(value = "/premium.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView premium(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("premium");
            return model;
        }
        return null;
    }

}
