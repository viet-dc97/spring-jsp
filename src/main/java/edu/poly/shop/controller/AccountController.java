
package edu.poly.shop.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import edu.poly.shop.reponsitory.AccountRepository;
import edu.poly.shop.model.Account;
import edu.poly.shop.model.dto.AccountForm;
import edu.poly.shop.service.ErrorManager;
import edu.poly.shop.service.MailService;
import edu.poly.shop.service.ParamService;
import edu.poly.shop.service.SessionService;

@Controller
@RequestMapping("/account")
public class AccountController {

    private static final String PATH_SAVE_ACCOUNT_IMG = "/assets/img/account/";
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletRequest rq;
    @Autowired
    SessionService sessionService;
    @Autowired
    ParamService paramService;
    @Autowired
    MailService mailService;
    @Autowired
    ErrorManager errorManager;
    @Autowired
    AccountRepository accountRepository;

    @GetMapping
    public String getIndex() {
        Account account = sessionService.get("account");
        AccountForm accountForm = new AccountForm();
        accountForm.setUsername(account.getUsername());
        accountForm.setPassword(account.getPassword());
        accountForm.setFullname(account.getFullname());
        accountForm.setEmail(account.getEmail());
        accountForm.setPhoto(account.getPhoto());
        rq.setAttribute("accountForm", accountForm);
        return "account";
    }

    @GetMapping("/update")
    public String returnIndex() {
        return "redirect:/account";
    }

    @RequestMapping("/update")
    public String updateAccount(
            @Valid @ModelAttribute("accountForm") AccountForm accountForm,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        errorManager.start("account");
        if (bindingResult.hasErrors()) {
            errorManager.add("form not valid!");
            return errorManager.path();
        }
        Account account = sessionService.getAccount();
        account.setUsername(accountForm.getUsername());
        account.setFullname(accountForm.getFullname());
        account.setEmail(accountForm.getEmail());
        if (!multipartFile.isEmpty()) {
            errorManager = paramService.saveImg(multipartFile, errorManager, PATH_SAVE_ACCOUNT_IMG);
            if (errorManager.exists()) {
                return errorManager.path();
            }
            account.setPhoto(errorManager.success());
        }
        if (!account.getPassword().equals(accountForm.getPassword())) {
            mailService.push(
                    account.getEmail(),
                    "[Shoppe] Mật khẩu đã được thay đổi!",
                    "Mật khẩu được thay đổi, nếu bạn không làm hãy báo việc này cho admin, cảm ơn!"
            );
            account.setPassword(accountForm.getPassword());
            errorManager.start("redirect:/logout");
        }
        accountRepository.save(account);
        errorManager.success("Cập nhật thành công!");
        return errorManager.path();
    }
}
