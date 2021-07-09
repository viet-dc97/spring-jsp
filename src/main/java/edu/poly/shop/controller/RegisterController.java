
package edu.poly.shop.controller;

import java.util.Date;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
import edu.poly.shop.model.dto.AccountRegister;
import edu.poly.shop.service.ErrorManager;

@Controller
@RequestMapping({"/register"})
public class RegisterController {

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    HttpServletRequest rq;
    @Autowired
    ErrorManager error;

    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("account-register", new AccountRegister());
        return "register";
    }

    @PostMapping
    public String register(
            @Valid @ModelAttribute("account-register") AccountRegister accountRegister,
            BindingResult bind
    ) {
        error.start("register", "redirect:/login");
        if (bind.hasErrors()) {
            error.add("Form not valid!");
            return error.path();
        }
        Optional<Account> optional = accountRepository.findById(accountRegister.getUsername());
        if (optional.isPresent()) {
            error.add("username is exists!");
            return error.path();
        }
        Account account = new Account(
                accountRegister.getUsername(),
                accountRegister.getPassword(),
                accountRegister.getFullname(),
                accountRegister.getEmail(),
                null, false, true, new Date()
        );
        accountRepository.save(account);
        return error.path();
    }
}
