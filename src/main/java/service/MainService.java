package service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

public class MainService {

    public void sendRedirectUserLogout(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            System.out.println("User Logout! Send redirect to index!");
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

}
