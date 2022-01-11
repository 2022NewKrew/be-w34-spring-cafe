package com.kakao.cafe;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/users").setViewName("/user/list");
        registry.addViewController("/signup").setViewName("/user/form");
        registry.addViewController("/articles").setViewName("/qna/form");
    }
}
