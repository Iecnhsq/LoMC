package controller;

import battle.Battle;
import entity.Card;
import entity.User;
import holders.BattlesHolder;
import holders.UserHolder;
import holders.WaitHolder;
import java.util.Random;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.CommonServiceInterface;
import service.WaitServiceInterface;

@Controller
public class WaitController {

    private static final Logger LOGGER = Logger.getLogger(WaitController.class);

    @Resource(name = "WaitServiceInterface")
    private WaitServiceInterface waitServiceInterface;
    @Resource(name = "CommonServiceInterface")
    private CommonServiceInterface commonServiceInterface;
    @Autowired
    private UserHolder uh;
    @Autowired
    private BattlesHolder bh;
    @Autowired
    private WaitHolder wh;

    @RequestMapping("/wait.html")
    public ModelAndView wait(HttpServletRequest request, HttpServletResponse response) {
        String login = (String) request.getSession().getAttribute("login");
        if (login == null) {
            commonServiceInterface.sendRedirectLoginNullInSesion(response);
        } else {
            ModelAndView model = new ModelAndView("wait");
            User u = uh.getUser();
            boolean inBattle = false;
            Set<Card> cards = waitServiceInterface.getUserCards(model, u.getClasss());
            if (cards.size() < 10) {
                waitServiceInterface.cardsLesssThenTen(response);
                return null;
            } else {
                for (int i : bh.keySet()) {
                    Battle b = bh.get(i);
                    if (b.p2.getLogin().equals(login)) {
                        request.getSession().setAttribute("battleId", i);
                        break;
                    }
                }
                try {
                    Battle inB = bh.get((Integer) request.getSession().getAttribute("battleId"));
                    if (inB != null) {
                        inBattle = waitServiceInterface.inBattle(login, inB);
                    }
                } catch (Exception ex) {
                    LOGGER.error("Error: " + ex + "You didnt have battleId");
                }
                if (!inBattle) {
                    Set<String> keylogin = wh.keySet();
                    model.addObject("classs", u.getClasss());
                    if (!keylogin.contains(login)) {
                        wh.put(login, uh.getUser());
                        int waitSize = wh.size();
                        if (waitSize < 2) {
                            model.addObject("login", login);
                            return model;
                        } else {
                            Battle b = new Battle();
                            b.p1 = uh.getUser();
                            wh.remove(b.p1.getLogin());
                            Set<String> waitKeys = wh.keySet();
                            if (b.p2.getLogin() != login) {
                                for (String key : waitKeys) {
                                    if (!key.equals(login)) {
                                        b.p2 = wh.remove(key);
                                        break;
                                    }
                                }
                                Integer i = new Random().nextInt();
                                bh.put(i, b);
                                request.getSession().setAttribute("battleId", i);
                                waitServiceInterface.getBattle(response);
                            }
                        }
                    } else {
                        waitServiceInterface.modelAddObject(model, u, login);
                        return model;
                    }
                } else {
                    waitServiceInterface.getBattle(response);
                }
            }
            return model;
        }
        return null;
    }

}
