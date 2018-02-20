package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CardsServiceInterface;
import service.CommonServiceInterface;

@Controller
public class CardsController {

    private static final Logger LOGGER = Logger.getLogger(CardsController.class);

    @Resource(name = "CardsServiceInterface")
    private CardsServiceInterface cardsServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/cards.html")
    public ModelAndView cards(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("cards");
            return model;
        }
        return null;
    }

}
