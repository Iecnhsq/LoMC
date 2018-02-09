package dao;

import entity.User;
import java.util.List;

public interface UserDAOInterface {

    public List<User> getAllUser();

    public User getUserByLogin(String login);

    public void addUser(User u);

    public void updateUser(User u);

    public boolean isExists(String login);

}
