/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.poly.shop.reponsitory.AccountRepository;
import edu.poly.shop.model.Account;

@Service
public class CookieService {

    @Autowired
    HttpServletRequest rq;
    @Autowired
    HttpServletResponse rp;
    @Autowired
    AccountRepository accountRepository;

    public Account getAccount() {
        Account account = null;
        String username = getValue("username");
        String password = getValue("password");
        if (username != null && password != null) {
            account = accountRepository.getAccount(username, password);
        }
        return account;
    }

    public Cookie get(String name) {
        Cookie[] cookies = rq.getCookies();
        Cookie result = null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) {
                result = cookie;
                break;
            }
        }
        return result;
    }

    public String getValue(String name) {
        Cookie cookie = this.get(name);
        return cookie == null ? null : cookie.getValue();
    }

    public void add(String name, String value, int hour) {
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(hour * 60 * 60);
        rp.addCookie(cookie);
    }

    public void remove(String name) {
        int len = rq.getCookies().length;
        for (Cookie cookie : rq.getCookies()) {
            if (cookie.getName().equalsIgnoreCase(name)) {
                Cookie c = cookie;
                cookie.setMaxAge(0);
                rp.addCookie(cookie);
                break;
            }
        }
    }

}
