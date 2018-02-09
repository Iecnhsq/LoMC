package dao;

import entity.Card;
import java.util.List;

public interface CardDAOInterface {

    public Card getCardById(int id, String cardClassName);

    public List<Card> getAllCards(String cardClassName);

    public List<Card> getCardsByLvl(String cardClassName);

}
