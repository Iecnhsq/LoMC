package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    @RequestMapping(value = "/register.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView register(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("register");
        return model;
    }

}
