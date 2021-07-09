
package edu.poly.shop.reponsitory;

import java.util.List;

import edu.poly.shop.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {

    @Query("SELECT od FROM OrderDetail od WHERE od.order.id=?1")
    public List<OrderDetail> findByIdOrder(int id);

    @Query("SELECT SUM(od.price * od.quantity) FROM OrderDetail od WHERE od.order.id=?1")
    public Double totalMoneyOrder(int id);

}
