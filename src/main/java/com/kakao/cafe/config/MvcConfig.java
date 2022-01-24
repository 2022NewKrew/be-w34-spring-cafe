package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/user/form").setViewName("/user/form");
        registry.addViewController("/user/login").setViewName("/user/login");
        registry.addViewController("/user/login_success").setViewName("/user/login_success");
        registry.addViewController("/user/login_failed").setViewName("/user/login_failed");
        registry.addRedirectViewController("/", "/index");
        registry.addViewController("/article/form").setViewName("/article/form");
    }
}
