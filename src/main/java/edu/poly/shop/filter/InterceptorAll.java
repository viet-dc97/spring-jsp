
package edu.poly.shop.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.shop.service.AccountSessionService;
import edu.poly.shop.service.CookieService;
import edu.poly.shop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import edu.poly.shop.model.Account;

@Component
public class InterceptorAll implements HandlerInterceptor {
    
    @Autowired
    AccountSessionService accountSessionService;
    @Autowired
    SessionService sessionService;
    @Autowired
    CookieService cookieService;
    
    @Override
    public boolean preHandle(HttpServletRequest rq, HttpServletResponse rp, Object handler) {
        Account account = sessionService.getAccount();
        if (account == null) {
            account = cookieService.getAccount();
        }
        accountSessionService.setAccount(account);
        return true;
    }
}
