package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry){
//        registry.addViewController("/").setViewName("index");
//        registry.addViewController("/users").setViewName("user/list");
        registry.addViewController("/users/form.html").setViewName("user/form");
        registry.addViewController("/login.html").setViewName("user/login");
        registry.addViewController("/posts/form.html").setViewName("post/form");

    }
}
