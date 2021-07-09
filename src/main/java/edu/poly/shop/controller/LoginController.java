
package edu.poly.shop.controller;

import java.util.Optional;
import javax.validation.Valid;

import edu.poly.shop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.poly.shop.reponsitory.AccountRepository;
import edu.poly.shop.model.Account;
import edu.poly.shop.model.dto.AccountLogin;
import edu.poly.shop.service.AccountSessionService;
import edu.poly.shop.service.CookieService;
import edu.poly.shop.service.ErrorManager;

@Controller
@RequestMapping({"/login"})
public class LoginController {
    
    @Autowired
    ErrorManager error;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CookieService cookieService;
    @Autowired
    SessionService sessionService;
    @Autowired
    AccountSessionService accountSessionService;
    
    @GetMapping
    public String getIndex(Model model) {
        if (sessionService.isLogin()) {
            return "redirect:/";
        }
        model.addAttribute("account-login", new AccountLogin());
        return "login";
    }
    
    @PostMapping
    public String login(
            @Valid @ModelAttribute("account-login") AccountLogin accountLogin,
            BindingResult bind
    ) {
        error.start("login", "redirect:/");
        if (bind.hasErrors()) {
            error.add("Form not valid!");
            return error.path();
        }
        Optional<Account> optional = accountRepository.findById(accountLogin.getUsername());
        if (!optional.isPresent()) {
            error.add("username không tồn tại!");
            return error.path();
        }
        Account account = accountRepository.getAccount(accountLogin.getUsername(), accountLogin.getPassword());
        if (account == null) {
            error.add("password không đúng!");
            return error.path();
        }
        cookieService.add("username", account.getUsername(), 24);
        cookieService.add("password", account.getPassword(), 24);
        sessionService.set("account", account);
        accountSessionService.setAccount(account);
        return error.path();
    }
}
