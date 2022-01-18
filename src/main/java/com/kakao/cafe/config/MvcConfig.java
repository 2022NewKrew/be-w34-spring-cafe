package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        registry.addViewController("/users/update").setViewName("updateform");
        registry.addViewController("/users/signup").setViewName("form");
        registry.addViewController("/users/login").setViewName("loginform");
        registry.addViewController("/users/success").setViewName("success");
        registry.addViewController("posts/form").setViewName("qnaform");
    }
}
