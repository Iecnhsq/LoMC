package service;

import entity.User;
import javax.servlet.http.HttpServletResponse;

public interface RegistrationServiceInterface {

    public boolean loginValid(String login);

    public User getUserInDB(String login);

    public boolean userExist(User u);

    public boolean passwordValid(String password, String password2);

    public boolean cityValid(String city);

    public boolean phoneValid(String phone);

    public boolean emailValid(String email);

    public boolean registerUserInDb(String login, String password, String city, String phone, String email);

    public void sendMailRegistrationInformation(String login, String password, String email);

    public void sendRedirectRegistersussefuly(HttpServletResponse response);

}
