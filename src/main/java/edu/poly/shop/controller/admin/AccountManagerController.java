/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.controller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import edu.poly.shop.reponsitory.AccountRepository;
import edu.poly.shop.model.Account;
import edu.poly.shop.service.ErrorManager;
import edu.poly.shop.service.ParamService;

@Controller
@RequestMapping("/admin/account-manager")
public class AccountManagerController {

    private static final String PATH_SAVE_ACCOUNT_IMG = "/assets/img/account/";
    @Autowired
    HttpServletRequest rq;
    @Autowired
    ErrorManager errorManager;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ParamService paramService;

    @GetMapping
    public String getIndex(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "col-sort", defaultValue = "time") String colSort,
            @RequestParam(name = "type-sort", defaultValue = "DESC") String typeSort,
            @RequestParam(name = "edit", defaultValue = "") String edit
    ) {
        Account account = new Account();
        if (!edit.equals("")) {
            account = accountRepository.findById(edit).get();
        }
        Page<Account> listAccounts = accountRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(typeSort), colSort));
        rq.setAttribute("listAccounts", listAccounts.getContent());
        rq.setAttribute("accountForm", account);
        rq.setAttribute("page", page);
        rq.setAttribute("typeSort", typeSort.equals("DESC") ? "ASC" : "DESC");
        return "admin/account-manager";
    }

    @GetMapping("/add")
    public String toIndex() {
        return "redirect:/admin/account-manager";
    }

    @RequestMapping("/add")
    public String updateAccount(
            @Valid @ModelAttribute("accountForm") Account account,
            BindingResult bindingResult,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        errorManager.start("admin/account-manager");
        if (bindingResult.hasErrors()) {
            errorManager.add("form not valid!");
            return errorManager.path();
        }
        String type = "Thêm thành công!";
        Account accountDB = accountRepository.getById(account.getUsername());
        if (accountDB == null) { // thêm mới
            String path = "";
            if (!multipartFile.isEmpty()) {
                errorManager = paramService.saveImg(multipartFile, errorManager, PATH_SAVE_ACCOUNT_IMG);
                if (errorManager.exists()) {
                    return errorManager.path();
                }
                account.setPhoto(errorManager.success());
            }
        } else { // sửa
            if (!multipartFile.isEmpty()) {
                errorManager = paramService.saveImg(multipartFile, errorManager, PATH_SAVE_ACCOUNT_IMG);
                if (errorManager.exists()) {
                    return errorManager.path();
                }
                account.setPhoto(errorManager.success());
            }
            type = "Sửa thành công!";
        }
        accountRepository.save(account);
        errorManager.success(type);
        return errorManager.path();
    }

}
