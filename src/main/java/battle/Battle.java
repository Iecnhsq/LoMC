package battle;

import entity.Card;
import entity.User;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import ourlists.OnTableList;

public class Battle {

    public User p1 = new User();
    public User p2 = new User();

    public List<Card> p1Deck = null;
    public List<Card> p2Deck = null;
    public int p1Health = 20;
    public int p2Health = 20;
    public int p1Mana = 1;
    public int p2Mana = 1;
    public int turn = 1;
    public List<Card> p1OnHand = new LinkedList<>();
    public List<Card> p2OnHand = new LinkedList<>();
    public OnTableList p1OnTable = new OnTableList();
    public OnTableList p2OnTable = new OnTableList();
    public Card p1AttackCard = null;
    public Card p2AttackCard = null;
    public boolean p1MadeTurn = false;
    public boolean p2MadeTurn = false;
    public Map<Integer, Card> p1ActiveCards = new HashMap<>();
    public Map<Integer, Card> p2ActiveCards = new HashMap<>();
    public boolean battleOn = false;
    public boolean p1Win = false;
    public boolean p2Win = false;
    public int p1points = 0;
    public int p2points = 0;
    public boolean p1CheckInFinish = false;
    public boolean p2CheckInFinish = false;
    public boolean p1HeroPowerSelected = false;
    public boolean p2HeroPowerSelected = false;
    public boolean p1Attacked = false;
    public boolean p2Attacked = false;
    public Card p1ChosenHandCard = null;
    public Card p2ChosenHandCard = null;
    
}
