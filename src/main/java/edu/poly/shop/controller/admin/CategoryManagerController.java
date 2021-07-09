/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.controller.admin;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import edu.poly.shop.reponsitory.CategoryRepository;
import edu.poly.shop.model.Category;
import edu.poly.shop.service.ErrorManager;

@Controller
@RequestMapping({"/admin/category-manager"})
public class CategoryManagerController {

    @Autowired
    HttpServletRequest rq;
    @Autowired
    ErrorManager error;
    @Autowired
    CategoryRepository categoryRepository;

    @GetMapping
    public String index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size,
            @RequestParam(name = "col-sort", defaultValue = "id") String colSort,
            @RequestParam(name = "type-sort", defaultValue = "DESC") String typeSort,
            @RequestParam(name = "edit", defaultValue = "") String edit
    ) {

        Page<Category> pageCategories = categoryRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(typeSort), colSort));
        List<Category> listCategories = pageCategories.getContent();

        Category category = new Category();
        if (!edit.equals("")) {
            int id = Integer.parseInt(edit);
            category = categoryRepository.getById(id);
        }
        //
        rq.setAttribute("category", category);
        rq.setAttribute("listcategory", listCategories);
        //
        rq.setAttribute("page", page);
        rq.setAttribute("typeSort", typeSort.equals("DESC") ? "ASC" : "DESC");
        return "admin/category-manager";
    }

    @GetMapping({"/add", "/delete"})
    public String returnIndex() {
        return "redirect:/admin/category-manager";
    }

//    @RequestMapping("/add")
    @PostMapping("/add")
    public String add(
            @Valid @ModelAttribute("category") Category category,
            BindingResult bind
    ) {
        error.start("admin/category-manager");
        if (bind.hasErrors()) {
            error.add("form not valid!");
            return error.path();
        }
        error.success(category.getId() == null ? "Thêm thành công!" : "Sửa thành công!");
        category.setTime(new Date());
        categoryRepository.save(category);
        return error.path();
    }

    @RequestMapping("/delete")
    public String delete(
            @Valid @ModelAttribute("category") Category category,
            BindingResult bind
    ) {
        error.start("admin/category-manager");
        if (bind.hasErrors()) {
            error.add("form not valid!");
            return error.path();
        }
        if (category.getId() == null) {
            error.add("Bạn phải nhập id!");
            return error.path();
        }
        categoryRepository.delete(category);
        error.success("Xóa thành công!");
        return error.path();
    }

}
