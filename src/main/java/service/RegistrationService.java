package service;

import dao.UserDAO;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;

public class RegistrationService {

    @Autowired
    UserDAO udao;

    public void sendRedirectLoginInSesion(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            System.out.println("Login not null! Send redirect to main!");
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

}
