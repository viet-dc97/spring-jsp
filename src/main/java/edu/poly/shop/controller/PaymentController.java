/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.poly.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import edu.poly.shop.reponsitory.OrderRepository;
import edu.poly.shop.reponsitory.OrderDetailRepository;
import edu.poly.shop.reponsitory.ProductRepository;
import edu.poly.shop.model.Account;
import edu.poly.shop.model.Order;
import edu.poly.shop.model.OrderDetail;
import edu.poly.shop.model.Product;
import edu.poly.shop.model.dto.PaymentForm;
import edu.poly.shop.service.AccountSessionService;
import edu.poly.shop.service.ErrorManager;
import edu.poly.shop.service.MailService;
import edu.poly.shop.service.SessionService;
import edu.poly.shop.service.ShoppingCartServiceImpl;
import edu.poly.shop.util.MailContent;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    SessionService sessionService;
    @Autowired
    ShoppingCartServiceImpl shoppingCartServiceImpl;
    @Autowired
    AccountSessionService accountSessionService;
    @Autowired
    MailService mailService;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletRequest rq;
    @Autowired
    ErrorManager error;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderRepository orderRepository;

    @GetMapping
    public String getIndex() {
        if (shoppingCartServiceImpl.getCount() == 0) {
            return "redirect:/";
        }
        Account account = sessionService.getAccount();
        PaymentForm paymentForm = new PaymentForm(account.getFullname(), "", "");
        rq.setAttribute("paymentForm", paymentForm);
        rq.setAttribute("listCarts", shoppingCartServiceImpl.get());
        rq.setAttribute("totalPayment", shoppingCartServiceImpl.totalPayment());
        return "payment";
    }

    @GetMapping({"/add"})
    public String redirectIndex() {
        return "redirect:/payment";
    }

    @RequestMapping("/add")
    public String payment(
            @Valid @ModelAttribute("paymentForm") PaymentForm paymentForm,
            BindingResult bindingResult
    ) {
        if (!sessionService.isLogin()) {
            return "redirect:/login";
        }
        if (shoppingCartServiceImpl.getCount() == 0) {
            return "redirect:/";
        }
        error.start("payment", "redirect:/");
        if (bindingResult.hasErrors()) {
            error.add("form not valid!");
            return error.path();
        }
        Account account = sessionService.getAccount();
        Order order = new Order();
        order.setAccount(account);
        order.setAndress(paymentForm.getAndress());
        order.setTime(new Date());
        orderRepository.save(order);
        List<OrderDetail> listOrderDetails = new ArrayList<>();
        shoppingCartServiceImpl.get().forEach((k, cart) -> {
            Product product = productRepository.getById(cart.getId());
            listOrderDetails.add(new OrderDetail(
                    null,
                    order,
                    product,
                    cart.getPrice(),
                    cart.getQty()
            ));
        });
        for (int i = 0; i < listOrderDetails.size(); i++) {
            OrderDetail orderDetail = orderDetailRepository.save(listOrderDetails.get(i));
            listOrderDetails.set(i, orderDetail);
        }
        MailContent mailContent = new MailContent(listOrderDetails);
        mailService.push(account.getEmail(), mailContent.buildTitle(), mailContent.buildBody());
        shoppingCartServiceImpl.clear();
        accountSessionService.setCountShoppingCart(0);

        return error.path();
    }
}
