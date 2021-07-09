
package edu.poly.shop.reponsitory;

import java.util.List;

import edu.poly.shop.model.Order;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.account.username = ?1")
    List<Order> findByUsernameEqual(String username);

    @Query("SELECT o FROM Order o WHERE o.account.username = ?1")
    List<Order> findByUsernameEqual(String username, Sort sort);

    @Query("SELECT o FROM Order o WHERE o.account.username = ?1")
    List<Order> findByUsernameEqual(String username, Pageable pageable);
}
