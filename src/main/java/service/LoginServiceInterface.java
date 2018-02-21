package service;

import entity.User;
import javax.servlet.http.HttpServletResponse;

public interface LoginServiceInterface {

    public User getUserInDB(String login);

    public boolean userExist(User u);

    public boolean passwordValid(String password, User u);

    public void sendRedirectLoginInUser(HttpServletResponse response);

    public void getAllCardsInDB();

}
