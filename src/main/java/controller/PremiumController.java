package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.CommonService;
import service.PremiumService;

@Controller
public class PremiumController {

    public static final Logger LOGGER = Logger.getLogger(PremiumController.class);

    @Autowired
    private PremiumService premiumService;
    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/premium.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView premium(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonService.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("premium");
            return model;
        }
        return null;
    }

}
