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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import edu.poly.shop.reponsitory.CategoryRepository;
import edu.poly.shop.reponsitory.ProductRepository;
import edu.poly.shop.model.Category;
import edu.poly.shop.model.Product;
import edu.poly.shop.model.dto.ProductForm;
import edu.poly.shop.service.ErrorManager;
import edu.poly.shop.service.ParamService;

@Controller
@RequestMapping({"/admin/product-manager"})
public class ProductManagerController {

    private static final String PATH_SAVE_PRODUCT_IMG = "/assets/img/product/";
    @Autowired
    HttpServletRequest rq;
    @Autowired
    ErrorManager error;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ParamService paramService;

    @GetMapping
    public String index(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "col-sort", defaultValue = "id") String colSort,
            @RequestParam(name = "type-sort", defaultValue = "DESC") String typeSort,
            @RequestParam(name = "edit", defaultValue = "") String edit
    ) {
        //
        Page<Product> pageProducts = productRepository.findAll(PageRequest.of(page, size, Sort.Direction.valueOf(typeSort), colSort));
        List<Product> listProducts = pageProducts.getContent();
        //
        ProductForm productForm = new ProductForm();
        if (!edit.equals("")) {
            int id = Integer.parseInt(edit);
            Product product = productRepository.getById(id);
            productForm.setCategoryId(product.getCategory().getId());
            productForm.setId(product.getId());
            productForm.setName(product.getName());
            productForm.setPrice(product.getPrice());
            productForm.setStatus(product.getStatus());
        }
        //
        rq.setAttribute("productForm", productForm);
        rq.setAttribute("listProduct", listProducts);
        rq.setAttribute("listCategory", categoryRepository.findAll());
        //
        rq.setAttribute("page", page);
        rq.setAttribute("typeSort", typeSort.equals("DESC") ? "ASC" : "DESC");
        return "admin/product-manager";
    }

    @GetMapping({"/add", "/delete"})
    public String returnIndex() {
        return "redirect:/admin/product-manager";
    }

    @RequestMapping("/add")
    public String add(
            @Valid @ModelAttribute("productForm") ProductForm productForm,
            BindingResult bind,
            @RequestParam("file") MultipartFile multipartFile
    ) {
        String type = "Thêm thành công";
        error.start("admin/product-manager");
        if (bind.hasErrors()) {
            error.add("form not valid!");
            return error.path();
        }
        Product product = null;
        if (productForm.getId() == null) { // thêm mới
            error = paramService.saveImg(multipartFile, error, PATH_SAVE_PRODUCT_IMG);
            if (error.exists()) {
                return error.path();
            }
            Category category = categoryRepository.getById(productForm.getCategoryId());
            product = new Product(
                    productForm.getId(),
                    productForm.getName(),
                    error.success(),
                    productForm.getPrice(),
                    true,
                    new Date(),
                    category,
                    null
            );
        } else { // sửa
            Category category = categoryRepository.getById(productForm.getCategoryId());
            product = productRepository.getById(productForm.getId());
            if (!multipartFile.isEmpty()) {
                error = paramService.saveImg(multipartFile, error, PATH_SAVE_PRODUCT_IMG);
                if (error.exists()) {
                    return error.path();
                }
                product.setImage(error.success());
            }
            product.setCategory(category);
            product.setName(productForm.getName());
            product.setPrice(productForm.getPrice());
            product.setStatus(productForm.getStatus());
            type = "Sửa thành công!";
        }
        productRepository.save(product);
        error.success(type);
        return error.path();
    }

    @RequestMapping("/delete")
    public String delete(
            @Valid @ModelAttribute("productForm") ProductForm productForm
    ) {
        error.start("admin/product-manager");
        if (productForm.getId() == null) {
            error.add("Bạn phải nhập id!");
            return error.path();
        }
        Product product = new Product();
        product.setId(productForm.getId());
        productRepository.delete(product);
        error.success("Xóa thành công!");
        return error.path();
    }

}
