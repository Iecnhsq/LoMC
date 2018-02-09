package service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "MainServiceInterface")
public class MainServiceImpl implements MainServiceInterface {

    public static final Logger LOGGER = Logger.getLogger(MainServiceImpl.class);

    @Override
    public void sendRedirectUserLogout(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            System.out.println("User Logout! Send redirect to index!");
        } catch (IOException ex) {
            System.out.println("Error: " + ex);
        }
    }

}
