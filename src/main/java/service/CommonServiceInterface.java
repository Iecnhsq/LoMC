package service;

import javax.servlet.http.HttpServletResponse;

public interface CommonServiceInterface {

    public void sendRedirectLoginNullInSesion(HttpServletResponse response);

    public void sendRedirectLoginInSesion(HttpServletResponse response);

}
