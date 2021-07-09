package edu.poly.shop.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import edu.poly.shop.reponsitory.ProductRepository;
import edu.poly.shop.model.dto.CartItem;

@SessionScope
@Service
public class ShoppingCartServiceImpl implements IShopService<CartItem> {

    @Autowired
    ProductRepository productRepository;

    Map<Integer, CartItem> listCarts = new HashMap<>();

    @Override
    public void add(CartItem cartItem) {
        int id = cartItem.getId();
        if (listCarts.containsKey(id)) {
            int qty = listCarts.get(id).getQty();
            listCarts.get(id).setQty(qty + 1);
        } else {
            listCarts.put(id, cartItem);
        }
    }

    @Override
    public void delete(CartItem cartItem) {
        listCarts.remove(cartItem.getId());
    }

    @Override
    public void clear() {
        listCarts.clear();
    }

    @Override
    public int getCount() {
        return listCarts.size();
    }

    @Override
    public double totalPayment(CartItem cartItem) {
        int id = cartItem.getId();
        double money = listCarts.get(id).getPrice();
        int qty = listCarts.get(id).getQty();
        return money * qty;
    }

    @Override
    public double totalPayment() {
        double d = 0.0;
        for (Map.Entry<Integer, CartItem> pair : listCarts.entrySet()) {
            d += totalPayment(pair.getValue());
        }
        return d;
    }

    @Override
    public Map<Integer, CartItem> get() {
        return listCarts;
    }

    @Override
    public void update(CartItem t) {
        if (listCarts.containsKey(t.getId())) {
            listCarts.put(t.getId(), t);
        }
    }

    @Override
    public CartItem get(int id) {
        return listCarts.get(id);
    }

}
