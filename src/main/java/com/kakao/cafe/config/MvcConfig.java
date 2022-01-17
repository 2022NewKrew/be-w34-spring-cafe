package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/articles");
        registry.addViewController("/signup").setViewName("/user/form");
        registry.addViewController("/articles/form").setViewName("/qna/form");
        registry.addViewController("/login").setViewName("/user/login");
    }
}
