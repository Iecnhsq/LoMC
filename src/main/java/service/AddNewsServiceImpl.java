package service;

import dao.NewsDAOInterface;
import entity.News;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service(value = "AddNewsServiceInterface")
public class AddNewsServiceImpl implements AddNewsServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(AddNewsServiceImpl.class);

    @Resource(name = "NewsDAOInterface")
    private NewsDAOInterface ndao;

    @Override
    public void getNewsAndLogin(ModelAndView model, String login) {
        List<News> allnews = ndao.getAllNews();
        model.addObject("allnews", allnews);
        model.addObject("login", login);
    }

    @Override
    public boolean addNewsInDb(String subject, String author, String text) {
        News news = new News(new Random().nextInt(), subject, author, new Date(), text);
        ndao.addNews(news);
        LOGGER.info("Add news in DB!");
        return true;
    }

}
