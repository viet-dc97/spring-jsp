
package edu.poly.shop.reponsitory;

import edu.poly.shop.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

@Service
public interface AccountRepository extends JpaRepository<Account, String> {

    @Query("SELECT account FROM Account account WHERE account.username=?1 AND account.password=?2")
    Account getAccount(String username, String password);
}
