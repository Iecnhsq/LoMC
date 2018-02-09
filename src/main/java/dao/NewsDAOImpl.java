package dao;

import entity.News;
import hibernate.HibernateUtil;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

@Repository(value = "NewsDAOInterface")
public class NewsDAOImpl implements NewsDAOInterface {

    private static final Logger LOGGER = Logger.getLogger(NewsDAOImpl.class);

    @Override
    public List<News> getAllNews() {
        List<News> getAllNews;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        getAllNews = s.createQuery("FROM News").list();
        s.close();
        LOGGER.info("Get all news: " + getAllNews);
        return getAllNews;
    }

    @Override
    public News getNewsById(int id) {
        News getNewsById;
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        getNewsById = (News) s.createQuery("FROM News WHERE id='" + id + "'").uniqueResult();
        s.getTransaction().commit();
        s.close();
        LOGGER.info("Get news by id: " + getNewsById);
        return getNewsById;
    }

    @Override
    public void addNews(News n) {
        Session s = HibernateUtil.getSESSIONFACTORY().openSession();
        s.beginTransaction();
        s.save(n);
        s.getTransaction().commit();
        s.close();
        LOGGER.info("Add news: " + n);
    }

    @Override
    public int getMaxId() {
        int maxId = 0;
        try {
            Session s = HibernateUtil.getSESSIONFACTORY().openSession();
            s.beginTransaction();
            Query q = s.createQuery("Select max(id) From News");
            List currentSeq = q.list();
            if (currentSeq.contains(null)) {
                maxId = 1;
                return maxId;
            } else {
                maxId = (int) currentSeq.get(0);
                return maxId + 1;
            }
        } catch (HibernateException ex) {
            LOGGER.error("Error in get max id: " + ex);
        }
        LOGGER.info("Max id is: " + maxId);
        return maxId;
    }

}
