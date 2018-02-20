package service;

import battle.Battle;
import entity.Card;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import ourlists.OnTableList;

public interface BattleServiceInterface {

    public void setDeckCards(Battle b);

    public void finish(HttpServletResponse response);

    public Card add1CardToHand(List<Card> pDeck);

    public int addPoint(int health, int damage, int points);

    public void endTurn(Battle b, String login);

    public void beginNewTurn(Battle b);

    public void p1CreatureTurn(Battle b, int id);

    public void p2CreatureTurn(Battle b, int id);

    public void p1HeroPowerAttack(Battle b, int id);

    public void p2HeroPowerAttack(Battle b, int id);

    public void p1PutCard(Card c, Battle b);

    public void p2PutCard(Card c, Battle b);

    public boolean doSpell(String spell, Battle b);

    public void clearDefeatedCard(OnTableList onTablep1, OnTableList onTablep2);

}
