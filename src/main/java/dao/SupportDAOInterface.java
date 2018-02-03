package dao;

import entity.Support;
import java.util.List;

public interface SupportDAOInterface {

    public void addSuppotLetter(Support support);

    public void updateSuppotLetter(Support support);

    public void removeSuppotLetter(int id);

    public List<Support> listSupport();

    public Support getSupportById(int id);

}
