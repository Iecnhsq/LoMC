package dao;

import entity.User;
import hibernate.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository(value = "UserDAOInterface")
public class UserDAOImpl implements UserDAOInterface {

    private static final Logger LOGGER = Logger.getLogger(UserDAOImpl.class);

    @Override
    public List<User> getAllUser() {
        List<User> getAllUser;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        getAllUser = s.createQuery("FROM User").list();
        s.close();
        LOGGER.info("Get all user: " + getAllUser);
        return getAllUser;
    }

    @Override
    public User getUserByLogin(String login) {
        User getUserByLogin;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        getUserByLogin = (User) s.createQuery("FROM User WHERE login='" + login + "'").uniqueResult();
        s.getTransaction().commit();
        s.close();
        LOGGER.info("Get user by login: " + getUserByLogin);
        return getUserByLogin;
    }

    @Override
    public void addUser(User u) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.save(u);
        s.getTransaction().commit();
        s.close();
        LOGGER.info("Add user:" + u);
    }

    @Override
    public void updateUser(User u) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.update(u);
        s.getTransaction().commit();
        s.close();
        LOGGER.info("Update user: " + u);
    }

    @Override
    public boolean isExists(String login) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        User u = (User) s.createQuery("FROM User WHERE login='" + login + "'").uniqueResult();
        LOGGER.info("User is: " + u);
        return u != null;
    }

}
