package service;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

public class CommonService {

    private static final Logger LOGGER = Logger.getLogger(CommonService.class);

    public void sendRedirectLoginNullInSesion(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            LOGGER.info("Login is null! Send redirect to index!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    public void sendRedirectLoginInSesion(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            LOGGER.info("Login not null! Send redirect to main!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
