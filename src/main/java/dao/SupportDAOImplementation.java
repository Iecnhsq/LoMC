package dao;

import entity.Support;
import hibernate.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.Session;

public class SupportDAOImplementation implements SupportDAOInterface {

    private static final Logger LOGGER = Logger.getLogger(SupportDAOImplementation.class);

    @Override
    public void addSuppotLetter(Support support) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.save(support);
        s.getTransaction().commit();
        LOGGER.info("Suport letter added successfully! Details : " + support);
        s.close();
    }

    @Override
    public void updateSuppotLetter(Support support) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.update(support);
        s.getTransaction().commit();
        LOGGER.info("Suport letter updated successfully! Details : " + support);
        s.close();
    }

    @Override
    public void removeSuppotLetter(int id) {
        LOGGER.info("Not supported yet!");
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Support> listSupport() {
        List<Support> listSupport;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        listSupport = s.createQuery("FROM Support").list();
        listSupport.forEach((support) -> {
            LOGGER.info("Support list: " + support);
        });
        s.close();
        return listSupport;
    }

    @Override
    public Support getSupportById(int id) {
        Support supportById;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        supportById = null;
        s.beginTransaction();
        supportById = (Support) s.createQuery("FROM Support WHERE id='" + id + "'").uniqueResult();
        s.getTransaction().commit();
        LOGGER.info("Suport letter getted by ID successfully! Details : " + supportById);
        s.close();
        return supportById;
    }

}
