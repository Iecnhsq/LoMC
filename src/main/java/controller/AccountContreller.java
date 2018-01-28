package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import service.AccountService;

@Controller
public class AccountContreller {

    @Autowired
    AccountService accountService;

    @RequestMapping("/account.html")
    public ModelAndView account(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView model = new ModelAndView("account");
        return model;
    }

}
