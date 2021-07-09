
package edu.poly.shop.controller;

import javax.servlet.http.HttpServletRequest;

import edu.poly.shop.service.ShoppingCartServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.poly.shop.model.dto.CartItem;
import edu.poly.shop.service.AccountSessionService;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletRequest rq;

    @Autowired
    ShoppingCartServiceImpl shoppingCartServiceImpl;
    @Autowired
    AccountSessionService accountSessionService;

    @GetMapping
    public String index() {
        rq.setAttribute("totalPayment", shoppingCartServiceImpl.totalPayment());
        rq.setAttribute("listCarts", shoppingCartServiceImpl.get());
        String path = "cart";
        if (shoppingCartServiceImpl.getCount() == 0) {
            path = "redirect:/";
        }
        return path;
    }

    @GetMapping("/remove/{id}")
    public String remove(
            @PathVariable(name = "id", required = true) int id
    ) {
        CartItem cartItem = new CartItem();
        cartItem.setId(id);
        shoppingCartServiceImpl.delete(cartItem);
        accountSessionService.setCountShoppingCart(shoppingCartServiceImpl.getCount());
        return "redirect:/shopping-cart";
    }

    @GetMapping("/{type}/{id}/count/{count}")
    public String setQty(
            @PathVariable(name = "type", required = true) String type,
            @PathVariable(name = "id", required = true) int id,
            @PathVariable(name = "count", required = true) int count
    ) {
        if (type.equals("down")) {
            count = -count;
        }
        CartItem cartItem = shoppingCartServiceImpl.get(id);
        int qty = cartItem.getQty() + count;
        cartItem.setQty(qty);
        if (qty > 0) {
            shoppingCartServiceImpl.update(cartItem);
        } else {
            shoppingCartServiceImpl.delete(cartItem);
        }
        return "redirect:/shopping-cart";
    }
}
