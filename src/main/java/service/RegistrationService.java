package service;

import dao.UserDAO;
import java.io.IOException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class RegistrationService {

    private static final Logger LOGGER = Logger.getLogger(RegistrationService.class);
    private static final String FROMMAIL = "cardgamesupp@gmail.com";

    @Autowired
    private UserDAO udao;
    @Autowired
    private JavaMailSender mailSender;

    public void sendRedirectLoginInSesion(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            LOGGER.info("Login not null! Send redirect to main!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    public void sendRedirectRegistersussefuly(HttpServletResponse response) {
        try {
            response.sendRedirect("account.html");
            LOGGER.info("Register User sussefuly! Redirect to account");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    public void sendMailRegistrationInformation(String login, String password, String email) {
        String emailSubject = "Your are register in 'Card Game'";
        StringBuilder sb = new StringBuilder();
        sb.append("Hi!").append("\n").append("You have just registered in 'Card Game'").append("\n")
                .append("Your Login: ").append(login).append("\n")
                .append("Your Password: ").append(password).append("\n")
                .append("Thank you, good game").append("\n").append("\n")
                .append("Sincerely, the Card Game team");
        String emailMessage = sb.toString();
        mailSender.send((MimeMessage mimeMessage) -> {
            MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMsgHelperObj.setTo(email);
            mimeMsgHelperObj.setFrom(FROMMAIL);
            mimeMsgHelperObj.setText(emailMessage);
            mimeMsgHelperObj.setSubject(emailSubject);
        });
    }

}
