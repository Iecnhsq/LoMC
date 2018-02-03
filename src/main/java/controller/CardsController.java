package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CardsService;
import service.CommonService;

@Controller
public class CardsController {

    public static final Logger LOGGER = Logger.getLogger(CardsController.class);

    @Autowired
    private CardsService cardsService;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/cards.html")
    public ModelAndView cards(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonService.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("cards");
            return model;
        }
        return null;
    }

}
