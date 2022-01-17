package com.kakao.cafe.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/form").setViewName("user/form");
        registry.addViewController("/article/form").setViewName("article/form");
        registry.addViewController("/user/loginForm").setViewName("user/loginForm");
    }

}
