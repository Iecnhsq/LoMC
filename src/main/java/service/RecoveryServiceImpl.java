package service;

import dao.UserDAOInterface;
import entity.User;
import java.io.IOException;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service(value = "RecoveryServiceInterface")
public class RecoveryServiceImpl implements RecoveryServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(RecoveryServiceImpl.class);
    private static final String FROMMAIL = "cardgamesupp@gmail.com";

    @Autowired
    private JavaMailSender mailSender;
    @Resource(name = "UserDAOInterface")
    private UserDAOInterface udao;

    @Override
    public User getUserInDB(String login) {
        return udao.getUserByLogin(login);
    }

    @Override
    public boolean userExist(User u) {
        return u != null;
    }

    @Override
    public boolean loginValid(String login, User u) {
        return !(login == null || login.length() < 5 || !u.getLogin().equals(login));
    }

    @Override
    public boolean emailValid(String email, User u) {
        return !(email == null || email.length() < 5 || !u.getEmail().equals(email));
    }

    @Override
    public boolean isAnswerEquals(String answer, String ca) {
        return !(answer == null || answer.length() != 10 || !answer.equals(ca));
    }

    @Override
    public void setAttribute(HttpServletRequest request, String email, User u, String ca) {
        request.getSession().setAttribute("email", email);
        request.getSession().setAttribute("password", u.getPass());
        request.getSession().setAttribute("ca", ca);
    }

    @Override
    public void sendRedirectSendRecoveryMail(HttpServletResponse response) {
        try {
            response.sendRedirect("answer.html");
            LOGGER.info("Send recovery mail! Send redirect to answer!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    @Override
    public void sendAndRedirectToIndex(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            LOGGER.info("Send recovery password to User! Send redirect to index!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    @Override
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

    @Override
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
