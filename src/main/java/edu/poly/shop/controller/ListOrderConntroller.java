
package edu.poly.shop.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import edu.poly.shop.reponsitory.OrderRepository;
import edu.poly.shop.reponsitory.OrderDetailRepository;
import edu.poly.shop.model.Account;
import edu.poly.shop.service.SessionService;

@Controller
@RequestMapping("/list-order")
public class ListOrderConntroller {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    HttpServletRequest rq;
    @Autowired
    SessionService sessionService;

    @GetMapping
    public String getIndex(
            @RequestParam(name = "view-detail", defaultValue = "-1") int idOrder,
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "5") int size,
            @RequestParam(name = "col-sort", defaultValue = "time") String colSort,
            @RequestParam(name = "type-sort", defaultValue = "DESC") String typeSort
    ) {
        if (idOrder != -1) {
            rq.setAttribute("idOrder", idOrder);
            rq.setAttribute("listOrderDetails", orderDetailRepository.findByIdOrder(idOrder));
            rq.setAttribute("totalMoneyOrder", orderDetailRepository.totalMoneyOrder(idOrder));
        }

        Account account = sessionService.getAccount();
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.valueOf(typeSort), colSort));
        rq.setAttribute("listOrders", orderRepository.findByUsernameEqual(account.getUsername(), pageable));
        rq.setAttribute("page", page);
        rq.setAttribute("typeSort", typeSort.equals("DESC") ? "ASC" : "DESC");
        return "list-order";
    }

}
