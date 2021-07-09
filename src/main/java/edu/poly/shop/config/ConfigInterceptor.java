
package edu.poly.shop.config;

import edu.poly.shop.filter.InterceptorAdmin;
import edu.poly.shop.filter.InterceptorAll;
import edu.poly.shop.filter.InterceptorGuest;
import edu.poly.shop.filter.InterceptorUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigInterceptor implements WebMvcConfigurer {

    @Autowired
    InterceptorUser interceptorUser;
    @Autowired
    InterceptorAdmin interceptorAdmin;
    @Autowired
    InterceptorGuest interceptorGuest;
    @Autowired
    InterceptorAll interceptorAll;

    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        interceptorRegistry.addInterceptor(interceptorAdmin)
                .addPathPatterns("/admin/*");
        interceptorRegistry.addInterceptor(interceptorUser)
                .addPathPatterns("/list-order", "/payment", "/logout", "/account");
        interceptorRegistry.addInterceptor(interceptorGuest)
                .addPathPatterns("/login", "/register");
        interceptorRegistry.addInterceptor(interceptorAll)
                .addPathPatterns("/**");
    }

}
