package edu.poly.shop.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.poly.shop.reponsitory.CategoryRepository;
import edu.poly.shop.reponsitory.ProductRepository;
import edu.poly.shop.model.dto.CartItem;
import edu.poly.shop.model.Category;
import edu.poly.shop.model.Product;
import edu.poly.shop.service.AccountSessionService;
import edu.poly.shop.service.ShoppingCartServiceImpl;

@Controller
@RequestMapping({"/"})
public class IndexController {
    
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    ProductRepository productRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletRequest rq;
    @Autowired
    ShoppingCartServiceImpl shoppingCartServiceImpl;
    @Autowired
    AccountSessionService accountSessionService;
    
    @GetMapping
    public String index() {
        List<Category> listCategories = categoryRepository.findAll();
        rq.setAttribute("listCategories", listCategories);
        return "index";
    }
    
    @GetMapping({"/cart/add/{id}"})
    public String addCart(
            @PathVariable(name = "id", required = true) int id
    ) {
        Product product = productRepository.getById(id);
        CartItem cartItem = new CartItem(product.getId(), product.getName(), product.getPrice(), 1);
        shoppingCartServiceImpl.add(cartItem);
        accountSessionService.setCountShoppingCart(shoppingCartServiceImpl.getCount());
        return "redirect:/";
    }    
}
