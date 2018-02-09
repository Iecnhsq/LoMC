package service;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "ADMServiceInterface")
public class ADMServiceImplementation implements ADMServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(ADMServiceImplementation.class);

    @Override
    public void sendRedirectAndRemoveAttributeLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getSession().removeAttribute("login");
            response.sendRedirect("index.html");
            LOGGER.info("Remove attribute Login adn redirect to index");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    @Override
    public void sendRedirectLoginNullInSesionOrNotAdmin(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            LOGGER.info("Login is null or not is Admin");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
