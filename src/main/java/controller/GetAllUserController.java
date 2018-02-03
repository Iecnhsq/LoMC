package controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetAllUserController {

    @RequestMapping(value = "/getAllUser.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView admTools(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null || !login.equals("admin")) {
            try {
                response.sendRedirect("index.html");
            } catch (IOException ex) {
                Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            ModelAndView model = new ModelAndView("getAllUser");
            String exit = request.getParameter("exit");
            if (exit != null) {
                try {
                    request.getSession().removeAttribute("login");
                    response.sendRedirect("index.html");
                } catch (IOException ex) {
                    Logger.getLogger(ADMController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                ///
            }
            return model;
        }
        return null;
    }

}
