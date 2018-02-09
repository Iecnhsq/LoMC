package service;

import entity.User;
import javax.servlet.http.HttpServletResponse;

public interface LoginServiceInterface {

    public boolean loginValid(String login);

    public User getUserInDB(String login);

    public boolean userExist(User u);

    public boolean passwordValid(String password, User u);

    public void sendRedirectLoginInUser(HttpServletResponse response);

}
