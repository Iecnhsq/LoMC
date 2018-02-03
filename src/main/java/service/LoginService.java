package service;

import dao.UserDAO;
import entity.User;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService {

    public static final Logger LOGGER = Logger.getLogger(LoginService.class);

    @Autowired
    private UserDAO udao;

    public User getUserInDB(String login) {
        return udao.getUserByLogin(login);
    }

    public boolean userExist(User u) {
        return u != null;
    }

    public boolean loginValid(String login) {
        return !(login == null || login.length() < 5);
    }

    public boolean passwordValid(String password, User u) {
        return !(password == null || !u.getPass().equals(password));
    }

    public void sendRedirectLoginInUser(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            LOGGER.info("Login In User! Send redirect to main!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
