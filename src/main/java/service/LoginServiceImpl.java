package service;

import dao.CardDAOInterface;
import dao.UserDAOInterface;
import entity.Card;
import entity.User;
import holders.CardHolder;
import java.io.IOException;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "LoginServiceInterface")
public class LoginServiceImpl implements LoginServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(LoginServiceImpl.class);

    @Resource(name = "UserDAOInterface")
    private UserDAOInterface udao;
    @Resource(name = "CardDAOInterface")
    private CardDAOInterface cdao;
    @Autowired
    private CardHolder ch;

    private List<Card> getCardsInDB(String cardClassName) {
        return cdao.getAllCards(cardClassName);
    }

    @Override
    public void getAllCardsInDB() {

        final String basicCard = "BasicCard";
        final String hunter = "Hunter";
        final String mage = "Mage";
        final String priest = "Priest";
        final String shaman = "Shaman";
        final String warrior = "Warrior";
        final String thief = "Thief";

        List<Card> BasicCard = getCardsInDB(basicCard);
        List<Card> Hunter = getCardsInDB(hunter);
        List<Card> Mage = getCardsInDB(mage);
        List<Card> Priest = getCardsInDB(priest);
        List<Card> Shaman = getCardsInDB(shaman);
        List<Card> Warrior = getCardsInDB(warrior);
        List<Card> Thief = getCardsInDB(thief);

        methodAdd(basicCard, BasicCard);
        methodAdd(hunter, Hunter);
        methodAdd(mage, Mage);
        methodAdd(priest, Priest);
        methodAdd(shaman, Shaman);
        methodAdd(thief, Thief);
        methodAdd(warrior, Warrior);
    }

    private void methodAdd(String className, List<Card> CardClass) {
        CardClass.forEach((c) -> {
            ch.putId(c.getId(), c);
        });
        ch.putClass(className, CardClass);
    }

    @Override
    public User getUserInDB(String login) {
        return udao.getUserByLogin(login);
    }

    @Override
    public boolean userExist(User u) {
        return u != null;
    }

    @Override
    public boolean passwordValid(String password, User u) {
        return u.getPass().equals(password);
    }

    @Override
    public void sendRedirectLoginInUser(HttpServletResponse response) {
        try {
            response.sendRedirect("main.html");
            LOGGER.info("Login In User! Send redirect to main!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

}
