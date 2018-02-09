package service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ADMServiceInterface {

    public void sendRedirectLoginNullInSesionOrNotAdmin(HttpServletResponse response);

    public void sendRedirectAndRemoveAttributeLogin(HttpServletRequest request, HttpServletResponse response);

}
