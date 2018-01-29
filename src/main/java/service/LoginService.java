package service;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

public class LoginService {

    public static final Logger LOGGER = Logger.getLogger(LoginService.class);

    @Autowired
    UserDAO udao;

    public void sendRedirectLoginInSesion(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            LOGGER.info("Login not null! Send redirect to main!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
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
