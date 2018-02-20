package service;

import entity.User;

public interface FinishServiceInterface {

    public int updatePoints(User player, int pointsGained);

    public void lvlUp(User u, int point);

}
