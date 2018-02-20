package service;

import dao.UserDAOInterface;
import entity.User;
import holders.UserHolder;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service(value = "FinishServiceInterface")
public class FinishServiceImpl implements FinishServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(FinishServiceImpl.class);

    @Resource(name = "UserDAOInterface")
    private UserDAOInterface udao;
    @Resource(name = "UserHolder")
    private UserHolder uh;

    @Override
    public int updatePoints(User player, int pointsGained) {
        player.setPoints(player.getPoints() + pointsGained);
        uh.set(player);
        udao.updateUser(player);
        return pointsGained;
    }

    @Override
    public void lvlUp(User u, int point) {
        int i = (int) (125 * Math.pow(1.25, u.getLvl()));
        if (i <= u.getPoints()) {
            int s = u.getLvl();
            s++;
            u.setLvl(s);
            uh.set(u);
            udao.updateUser(u);
        }
    }

}
