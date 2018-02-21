package service;

import entity.News;
import dao.NewsDAOInterface;
import dao.SupportDAOInterface;
import entity.Support;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

@Service(value = "AboutNewsSupportServiceInterface")
public class AboutNewsSupportServiceImpl implements AboutNewsSupportServiceInterface {

    private static final Logger LOGGER = Logger.getLogger(AboutNewsSupportServiceImpl.class);
    private static final String TOMAIL = "cardgamesupp@gmail.com";

    @Resource(name = "NewsDAOInterface")
    private NewsDAOInterface ndao;
    @Resource(name = "SupportDAOInterface")
    private SupportDAOInterface sdao;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void getNews(ModelAndView model) {
        List<News> allnews = ndao.getAllNews();
        model.addObject("allnews", allnews);
    }

    @Override
    public void sendMail(String problem, String message, String email, String subject) {
        StringBuilder sb = new StringBuilder();
        sb.append(problem).append("\n").append("\n").append(message).append("\n").append("\n");
        String emailMessage = sb.toString();
        mailSender.send((MimeMessage mimeMessage) -> {
            MimeMessageHelper mimeMsgHelperObj = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMsgHelperObj.setTo(TOMAIL);
            mimeMsgHelperObj.setFrom(email);
            mimeMsgHelperObj.setText(emailMessage);
            mimeMsgHelperObj.setSubject(subject);
        });
    }

    @Override
    public void sendRedirectSendMessageSuccessfully(HttpServletResponse response) {
        try {
            response.sendRedirect("index.html");
            LOGGER.info("Send message successfully! Send redirect to support!");
        } catch (IOException ex) {
            LOGGER.error("Error: " + ex);
        }
    }

    @Override
    public boolean addSupportLetterInDb(String problem, String subject, String message, String email) {
        Support support = new Support(new Random().nextInt(), problem, subject, email, message, new Date());
        sdao.addSuppotLetter(support);
        LOGGER.info("Add support letter in DB!");
        return true;
    }

}
