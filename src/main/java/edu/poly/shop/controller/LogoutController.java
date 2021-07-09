package edu.poly.shop.controller;

import edu.poly.shop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.poly.shop.service.AccountSessionService;
import edu.poly.shop.service.CookieService;
import edu.poly.shop.service.ShoppingCartServiceImpl;

@Controller
@RequestMapping("/logout")
public class LogoutController {

    @Autowired
    SessionService sessionService;
    @Autowired
    CookieService cookieService;
    @Autowired
    AccountSessionService accountSessionService;
    @Autowired
    ShoppingCartServiceImpl shoppingCartServiceImpl;

    @GetMapping
    public String logout() {
        sessionService.remove("account");
        cookieService.remove("username");
        cookieService.remove("password");
        shoppingCartServiceImpl.clear();
        accountSessionService.setAccount(null);
        accountSessionService.setCountShoppingCart(0);
        return "redirect:/";
    }
}
