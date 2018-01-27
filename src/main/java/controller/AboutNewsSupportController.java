package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AboutNewsSupportController {

    @RequestMapping("/about.html")
    public ModelAndView about(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("about");
        return model;
    }

    @RequestMapping("/news.html")
    public ModelAndView news(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("news");
        return model;
    }

    @RequestMapping(value = "/support.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView support(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("support");
        return model;
    }

}
