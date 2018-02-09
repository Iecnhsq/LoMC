package service;

import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;

public interface AboutNewsSupportServiceInterface {

    public void getNews(ModelAndView model);

    public void sendMail(String problem, String message, String email, String subject);

    public void sendRedirectSendMessageSuccessfully(HttpServletResponse response);

    public boolean addSupportLetterInDb(String problem, String subject, String message, String email);

}
