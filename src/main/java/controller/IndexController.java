package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import service.CommonServiceInterface;

@Controller
public class IndexController {

    private static final Logger LOGGER = Logger.getLogger(IndexController.class);

    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String viewIndex(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            LOGGER.info("Кто балуется - " + loginInSesion);
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            return "index";
        }
        return null;
    }

    @GetMapping("/ErrorPage403")
    public String error403() {
        return "ErrorPage403";
    }

}
