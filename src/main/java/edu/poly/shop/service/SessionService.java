
package edu.poly.shop.service;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.poly.shop.reponsitory.AccountRepository;
import edu.poly.shop.model.Account;

@Service
public class SessionService {

    @Autowired
    HttpSession session;
    @Autowired
    CookieService cookieService;
    @Autowired
    AccountSessionService accountSessionService;
    @Autowired
    AccountRepository accountRepository;

    public <T> T get(String name) {
        return (T) session.getAttribute(name);
    }

    public void set(String name, Object value) {
        session.setAttribute(name, value);
    }

    public void remove(String name) {
        session.removeAttribute(name);
    }

    public Account getAccount() {
        return get("account");
    }

    public boolean isLogin() {
        Account account = get("account");
        return account != null;
    }

    public boolean isAdmin() {
        Account account = get("account");
        return isLogin() && account.getRole();
    }
}
