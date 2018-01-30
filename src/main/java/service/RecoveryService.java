package service;

import java.io.IOException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

public class RecoveryService {

    private static final Logger LOGGER = Logger.getLogger(RecoveryService.class);
    private static final String FROMMAIL = "cardgamesupp@gmail.com";

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

    public void sendRedirectSendRecoveryMail(HttpServletResponse response) {
        try {
            response.sendRedirect("answer.html");
            LOGGER.info("Send recovery mail! Send redirect to answer!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    public void sendAndRedirectToIndex(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            LOGGER.info("Send recovery password to User! Send redirect to index!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    public void sendMailRecoveryKey(String ca, String email) {
        String emailSubject = "Your are recovery password in 'Card Game'";
        StringBuilder sb = new StringBuilder();
        sb.append("Hi!").append("\n").append("You have just recovery password in 'Card Game'")
                .append("\n").append("Your code: ").append(ca)
                .append("\n").append("If you did not do this, contact support immediately!")
                .append("\n").append("\n")
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

    public void sendMailRecoveryPassword(String password, String email) {
        String emailSubject = "Your are recovery password in 'Card Game'";
        StringBuilder sb = new StringBuilder();
        sb.append("Hi!").append("\n").append("You have just recovery password in 'Card Game'")
                .append("\n").append("Your Password: ").append(password)
                .append("\n").append("\n")
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
