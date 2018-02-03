package service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class MainService {

    public static final Logger LOGGER = Logger.getLogger(MainService.class);

    public void sendRedirectUserLogout(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            System.out.println("User Logout! Send redirect to index!");
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

}
