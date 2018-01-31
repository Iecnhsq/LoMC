package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.RecoveryService;

@Controller
public class AnswerController {

    private static final Logger LOGGER = Logger.getLogger(AnswerController.class);

    @Autowired
    private RecoveryService recoveryService;

    @RequestMapping("/answer.html")
    public ModelAndView answer(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            recoveryService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("answer");
            String ca = (String) request.getSession().getAttribute("ca");
            String answer = request.getParameter("answer");
            if (answer == null || answer.length() != 10 || !answer.equals(ca)) {
                return new ModelAndView("answer");
            } else {
                String email = (String) request.getSession().getAttribute("email");
                String password = (String) request.getSession().getAttribute("password");
                recoveryService.sendMailRecoveryPassword(password, email);
                recoveryService.sendAndRedirectToIndex(response);
            }
            return model;
        }
        return null;
    }

}
