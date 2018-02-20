package controller;

import battle.Battle;
import holders.BattlesHolder;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.BattleServiceInterface;
import service.CommonServiceInterface;

@Controller
public class BattleController {

    private static final Logger LOGGER = Logger.getLogger(BattleController.class);

    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;
    @Resource(name = "BattleServiceInterface")
    private BattleServiceInterface bs;
    @Autowired
    private BattlesHolder bh;

    @RequestMapping("/battle.html")
    public ModelAndView fight(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView();
        String login = ((String) request.getSession().getAttribute("login"));
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            Battle b = null;
            try {
                b = bh.get((Integer) request.getSession().getAttribute("battleId"));
            } catch (Exception eх) {
                LOGGER.error("Error: " + eх);
                commonServiceInterface.sendRedirectLoginInSesion(response);
                return null;
            }
            // Проверяем сформирован ли бой по признаку p1Deck==null
            if (b.p1Deck == null) {
                bs.setDeckCards(b);
            }
            //начало нового хода, можно потом поменять чтобы было как в хс
            String endOfTurn = request.getParameter("end");
            if (endOfTurn != null) {
                bs.endTurn(b, login);
            }
            if (b.p1MadeTurn && b.p2MadeTurn) {
                bs.beginNewTurn(b);
            }
            String idString = request.getParameter("id");
            if (b.p1.getLogin().equals(login) && !b.p1MadeTurn) {
                if (idString != null) {
                    int id = Integer.parseInt(idString);
                    //у карт противника id будут передаваться с минусом
                    if (!b.p1HeroPowerSelected) {
                        bs.p1CreatureTurn(b, id);
                    } else {
                        bs.p1HeroPowerAttack(b, id);
                    }
                } else if (request.getParameter("heroAttack") != null) {
                    b.p1AttackCard = null;
                    if (b.p1Mana >= 2 && !b.p1Attacked) {
                        b.p1HeroPowerSelected = !b.p1HeroPowerSelected;
                    }
                } else if (request.getParameter("putCard") != null && b.p1ChosenHandCard != null) {
                    String spell = b.p1ChosenHandCard.getSpell();
                    bs.p1PutCard(b.p1ChosenHandCard, b);
                    bs.doSpell(spell, b);

                }
            }

            if (b.p2.getLogin().equals(login) && b.p1MadeTurn) {
                if (idString != null) {
                    int id = Integer.parseInt(idString);
                    if (!b.p2HeroPowerSelected) {
                        bs.p2CreatureTurn(b, id);
                    } else {
                        bs.p2HeroPowerAttack(b, id);
                    }
                } else if (request.getParameter("heroAttack") != null) {
                    b.p2AttackCard = null;
                    if (b.p2Mana >= 2 && !b.p2Attacked) {
                        b.p2HeroPowerSelected = !b.p2HeroPowerSelected;
                    }
                } else if (request.getParameter("putCard") != null && b.p2ChosenHandCard != null) {
                    String spell = b.p2ChosenHandCard.getSpell();
                    bs.p2PutCard(b.p2ChosenHandCard, b);
                    bs.doSpell(spell, b);
                }
                bs.clearDefeatedCard(b.p1OnTable, b.p2OnTable);
            }
            if (b.p2Health <= 0) {
                b.p1Win = true;
                bs.finish(response);
                return null;
            }
            if (b.p1Health <= 0) {
                b.p2Win = true;
                bs.finish(response);
                return null;
            }
            model.addObject("p1Attacked", b.p1Attacked);
            model.addObject("p2Attacked", b.p2Attacked);
            model.addObject("p1HeroPowerSelected", b.p1HeroPowerSelected);
            model.addObject("p2HeroPowerSelected", b.p2HeroPowerSelected);
            model.addObject("p1TauntCards", b.p1OnTable.getTauntCards());
            model.addObject("p2TauntCards", b.p2OnTable.getTauntCards());
            model.addObject("p1HasTaunt", b.p1OnTable.checkTaunt());
            model.addObject("p2HasTaunt", b.p2OnTable.checkTaunt());
            model.addObject("Char1Health", b.p1Health);
            model.addObject("Char2Health", b.p2Health);
            model.addObject("Char1Mana", b.p1Mana);
            model.addObject("Char2Mana", b.p2Mana);
            model.addObject("class1", b.p1.getClasss());
            model.addObject("class2", b.p2.getClasss());
            model.addObject("onHandP1", b.p1OnHand);
            model.addObject("onHandP2", b.p2OnHand);
            model.addObject("deckP1", b.p1Deck);
            model.addObject("deckP2", b.p2Deck);
            model.addObject("p1", b.p1.getLogin());
            model.addObject("p2", b.p2.getLogin());
            model.addObject("onTableP1", b.p1OnTable);
            model.addObject("onTableP2", b.p2OnTable);
            model.addObject("p2turn", b.p1MadeTurn);
            model.addObject("p1turn", !b.p1MadeTurn);
            model.addObject("p1ActiveCardsIds", b.p1ActiveCards.keySet());
            model.addObject("p2ActiveCardsIds", b.p2ActiveCards.keySet());
            if (b.p1AttackCard != null) {
                model.addObject("p1AttackCardId", b.p1AttackCard.getId());
            }
            if (b.p2AttackCard != null) {
                model.addObject("p2AttackCardId", b.p2AttackCard.getId());
            }
            if (b.p1.getLogin().equals(login)) {
                model.addObject("p1Logged", true);
                model.addObject("p2Logged", false);
            } else {
                model.addObject("p1Logged", false);
                model.addObject("p2Logged", true);
            }
            return model;
        }
        return null;
    }

}
