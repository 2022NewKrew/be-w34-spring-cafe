package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("signup").setViewName("users/form");
        registry.addViewController("article/form").setViewName("articles/form");
        registry.addViewController("").setViewName("/articles");
        //registry.addRedirectViewController("", "/articles");
    }
}
