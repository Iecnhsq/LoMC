package service;

import java.util.Map;
import javax.servlet.http.HttpServletResponse;

public interface AboutNewsSupportServiceInterface {

    public void getNews(Map<String, Object> model);

    public void sendMail(String problem, String message, String email, String subject);

    public void sendRedirectSendMessageSuccessfully(HttpServletResponse response);

    public boolean addSupportLetterInDb(String problem, String subject, String message, String email);

}
