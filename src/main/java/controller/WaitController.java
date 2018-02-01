package controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.WaitService;

@Controller
public class WaitController {

    public static final Logger LOGGER = Logger.getLogger(WaitController.class);

    @Autowired
    WaitService waitService;

    @RequestMapping("/wait.html")
    public ModelAndView wait(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            try {
                response.sendRedirect("index.html");
                LOGGER.info("Login is null! Send redirect to index!");
            } catch (IOException ex) {
                LOGGER.error("Error: " + ex);
            }
        } else {
            ModelAndView model = new ModelAndView("wait");
            return model;
        }
        return null;
    }

}
