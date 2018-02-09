package controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.RecoveryServiceInterface;

@Controller
public class AnswerController {

    private static final Logger LOGGER = Logger.getLogger(AnswerController.class);

    @Resource(name = "RecoveryServiceInterface")
    private RecoveryServiceInterface recoveryServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;

    @RequestMapping("/answer.html")
    public ModelAndView answer(HttpServletRequest request, HttpServletResponse response) {
        String loginInSesion = (String) request.getSession().getAttribute("login");
        if (loginInSesion != null) {
            commonServiceInterface.sendRedirectLoginInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("answer");
            String answer = request.getParameter("answer");
            String ca = (String) request.getSession().getAttribute("ca");
            if (!recoveryServiceInterface.isAnswerEquals(answer, ca)) {
                LOGGER.info("Answer is not equals!");
                return model;
            } else {
                String email = (String) request.getSession().getAttribute("email");
                String password = (String) request.getSession().getAttribute("password");
                recoveryServiceInterface.sendMailRecoveryPassword(password, email);
                recoveryServiceInterface.sendAndRedirectToIndex(response);
            }
        }
        return null;
    }

}
