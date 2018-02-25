package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import service.ADMServiceInterface;

@Controller
public class ADMController {

    @Resource(name = "ADMServiceInterface")
    private ADMServiceInterface aDMServiceInterface;

    @RequestMapping(value = "/admTools.html", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView admTools(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (aDMServiceInterface.loginRequest(login)) {
            aDMServiceInterface.sendRedirectLoginNullInSesionOrNotAdmin(response);
        } else {
            ModelAndView model = new ModelAndView("admTools");
            String exit = request.getParameter("exit");
            if (exit != null) {
                aDMServiceInterface.sendRedirectAndRemoveAttributeLogin(request, response);
            } else {
                ///
            }
            model.addObject("login", login);
            return model;
        }
        return null;
    }

}
