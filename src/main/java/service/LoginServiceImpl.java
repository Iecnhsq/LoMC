package service;

import dao.UserDAOInterface;
import entity.User;
import java.io.IOException;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "LoginServiceInterface")
public class LoginServiceImpl implements LoginServiceInterface {

    public static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

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
    public boolean loginValid(String login) {
        return !(login == null || login.length() < 1);
    }

    @Override
    public boolean passwordValid(String password, User u) {
        return !(password == null || !u.getPass().equals(password));
    }

    @Override
    public void sendRedirectLoginInUser(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            LOGGER.info("Login In User! Send redirect to main!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
