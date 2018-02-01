package service;

import dao.NewsDAO;
import entity.News;
import java.io.IOException;
import java.util.List;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.servlet.ModelAndView;

public class AboutNewsSupportService {

    public static final Logger LOGGER = Logger.getLogger(AboutNewsSupportService.class);
    private static final String TOMAIL = "cardgamesupp@gmail.com";

    @Autowired
    private NewsDAO ndao;
    @Autowired
    private JavaMailSender mailSender;

    private boolean getProblem(String problem) {
        return !(problem == null || problem.length() <= 10);
    }

    private boolean getSubject(String subject) {
        return !(subject == null || subject.length() <= 10);
    }

    private boolean getEmail(String email) {
        return !(email == null || email.length() <= 5);
    }

    private boolean getMessage(String message) {
        return !(message == null || (message.length() <= 25 && message.length() > 250));
    }

    public boolean getAllParamPSEM(String problem, String subject, String email, String message) {
        return !(getProblem(problem) && getSubject(subject) && getEmail(email) && getMessage(message));
    }

    public void getNews(ModelAndView model) {
        List<News> allnews = ndao.getNews();
        model.addObject("allnews", allnews);
    }

    public void sendMail(String problem, String message, String email, String subject) {
        StringBuilder sb = new StringBuilder();
        sb.append(problem).append("\n").append("\n").append(message).append("\n").append("\n");
        String emailMessage = sb.toString();
        mailSender.send((MimeMessage mimeMessage) -> {
            MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMsgHelperObj.setTo(TOMAIL);
            mimeMsgHelperObj.setFrom(email);
            mimeMsgHelperObj.setText(emailMessage);
            mimeMsgHelperObj.setSubject(subject);
        });
    }

    public void sendRedirectLoginInSesion(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            LOGGER.info("Login not null! Send redirect to main!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    public void sendRedirectSendMessageSuccessfully(HttpServletResponse response) {
        try {
            response.sendRedirect("support.html");
            LOGGER.info("Send message successfully! Send redirect to support!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
