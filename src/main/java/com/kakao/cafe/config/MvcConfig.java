package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/users/form").setViewName("users/form");
        registry.addViewController("/users/login").setViewName("users/login");
        registry.addViewController("/articles/form").setViewName("articles/form");
    }
}
