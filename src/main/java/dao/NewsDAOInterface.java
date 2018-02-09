package dao;

import entity.News;
import java.util.List;

public interface NewsDAOInterface {

    public List<News> getAllNews();

    public News getNewsById(int id);

    public void addNews(News n);

    public int getMaxId();

}
