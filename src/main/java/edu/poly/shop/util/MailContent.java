
package edu.poly.shop.util;

import java.util.ArrayList;
import java.util.List;

import edu.poly.shop.model.OrderDetail;

public class MailContent {

    private final String title;
    private final String body;
    //
    private List<OrderDetail> listOrderDetails = new ArrayList<>();

    public MailContent(List<OrderDetail> listOrderDetails) {
        this.listOrderDetails = listOrderDetails;
        title = "[SHOPEE] Bạn vừa đặt một hàng mới!";
        body = "Bạn vừa đặt hàng thành công trên shop, shipper sẽ gọi điện cho bạn sớm thôi!";
    }

    public String buildTitle() {
        return title;
    }

    public String buildBody() {
        return body;
    }
}
