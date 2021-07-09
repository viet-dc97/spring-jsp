
package edu.poly.shop.filter;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.poly.shop.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import edu.poly.shop.model.Account;

@Component
public class InterceptorAdmin implements HandlerInterceptor {

    @Autowired
    SessionService sessionService;

    @Override
    public boolean preHandle(HttpServletRequest rq, HttpServletResponse rp, Object handler) throws IOException {
        Account account = sessionService.getAccount();
        if (account == null) {
            rp.sendRedirect(rq.getContextPath() + "/login");
            return false;
        }
        if (!account.getRole()) {
            rp.sendRedirect(rq.getContextPath() + "/");
            return false;
        }
        return true;
    }
}
