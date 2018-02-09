package service;

import com.google.gson.Gson;
import dao.UserDAOInterface;
import entity.Deck;
import entity.User;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service(value = "RegistrationServiceInterface")
public class RegistrationServiceImpl implements RegistrationServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(RegistrationServiceImpl.class);
    private static final String FROMMAIL = "cardgamesupp@gmail.com";

    @Resource(name = "UserDAOInterface")
    private UserDAOInterface udao;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public User getUserInDB(String login) {
        return udao.getUserByLogin(login);
    }

    @Override
    public boolean registerUserInDb(String login, String password, String city, String phone, String email) {
        User user = new User(new Random().nextInt(), login, password, new Date(), 0, 0, new Gson().toJson(new Deck()), "Mage", 0, city, phone, email, 'n');
        udao.addUser(user);
        LOGGER.info("Registre User in DB!");
        return true;
    }

    @Override
    public boolean userExist(User u) {
        return u != null;
    }

    @Override
    public boolean loginValid(String login) {
        return !(login == null || login.length() < 5);
    }

    @Override
    public boolean passwordValid(String password, String password2) {
        return !(password == null || password2 == null || !password.equals(password2));
    }

    @Override
    public boolean cityValid(String city) {
        return !(city == null || city.length() < 3);
    }

    @Override
    public boolean phoneValid(String phone) {
        return !(phone == null || phone.length() < 10);
    }

    @Override
    public boolean emailValid(String email) {
        return !(email == null || email.length() < 5);
    }

    @Override
    public void sendRedirectRegistersussefuly(HttpServletResponse response) {
        try {
            response.sendRedirect("account.html");
            LOGGER.info("Register User sussefuly! Redirect to account");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    @Override
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
