package service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "MainServiceInterface")
public class MainServiceImpl implements MainServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(MainServiceImpl.class);

    @Override
    public void sendRedirectUserLogout(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            LOGGER.info("User Logout! Send redirect to index!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
