package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonService;
import service.RecoveryService;

@Controller
public class AnswerController {

    private static final Logger LOGGER = Logger.getLogger(AnswerController.class);

    @Autowired
    private RecoveryService recoveryService;
    @Autowired
    private CommonService commonService;

    @RequestMapping("/answer.html")
    public ModelAndView answer(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonService.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("answer");
            String answer = request.getParameter("answer");
            String ca = (String) request.getSession().getAttribute("ca");
            if (!recoveryService.isAnswerEquals(answer, ca)) {
                LOGGER.info("Answer is not equals!");
                return model;
            } else {
                String email = (String) request.getSession().getAttribute("email");
                String password = (String) request.getSession().getAttribute("password");
                recoveryService.sendMailRecoveryPassword(password, email);
                recoveryService.sendAndRedirectToIndex(response);
            }
        }
        return null;
    }

}
