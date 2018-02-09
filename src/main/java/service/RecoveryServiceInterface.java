package service;

import entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface RecoveryServiceInterface {

    public boolean isAnswerEquals(String answer, String ca);

    public void sendMailRecoveryPassword(String password, String email);

    public void sendAndRedirectToIndex(HttpServletResponse response);

    public User getUserInDB(String login);

    public boolean userExist(User u);

    public boolean loginValid(String login, User u);

    public boolean emailValid(String email, User u);

    public void sendMailRecoveryKey(String ca, String email);

    public void setAttribute(HttpServletRequest request, String email, User u, String ca);

    public void sendRedirectSendRecoveryMail(HttpServletResponse response);

}
