package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.ADMServiceInterface;

@Controller
public class GetAllUserController {

    private static final Logger LOGGER = Logger.getLogger(GetAllUserController.class);

    @Resource(name = "ADMServiceInterface")
    private ADMServiceInterface aDMServiceInterface;

    @RequestMapping(value = "/getAllUser.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView getAllUser(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null || !login.equals("admin")) {
            aDMServiceInterface.sendRedirectLoginNullInSesionOrNotAdmin(response);
        } else {
            ModelAndView model = new ModelAndView("getAllUser");
            String exit = request.getParameter("exit");
            if (exit != null) {
                aDMServiceInterface.sendRedirectAndRemoveAttributeLogin(request, response);
            } else {
                ///
            }
            return model;
        }
        return null;
    }

}
