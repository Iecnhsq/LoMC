package dao;

import entity.Card;
import hibernate.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository(value = "CardDAOInterface")
public class CardDAOImpl implements CardDAOInterface {

    private static final Logger LOGGER = Logger.getLogger(CardDAOImpl.class);

    @Override
    public Card getCardById(int id, String cardClassName) {
        Card getCardById;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        getCardById = (Card) s.createQuery("FROM " + cardClassName + " WHERE id=" + id + "").uniqueResult();
        s.getTransaction().commit();
        s.close();
        LOGGER.info(getCardById(getCardById, cardClassName));
        return getCardById;
    }

    @Override
    public List<Card> getAllCards(String cardClassName) {
        List<Card> getAllCards;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        getAllCards = s.createQuery("From " + cardClassName + "").list();
        s.close();
        LOGGER.info("Get all cards: " + getAllCards);
        return getAllCards;
    }

    @Override
    public List<Card> getCardsByLvl(String cardClassName) {
        List<Card> getCardsByLvl;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        getCardsByLvl = s.createQuery("From " + cardClassName + " Card Level").list();
        s.close();
        LOGGER.info("Get cards by lvl: " + getCardsByLvl);
        return getCardsByLvl;
    }

    private String getCardById(Card getCardById, String cardClassName) {
        StringBuilder sb = new StringBuilder();
        sb.append("Get card by id: ").append(getCardById).append("\n").append("Card Class Name: ").append(cardClassName);
        return sb.toString();
    }

}
