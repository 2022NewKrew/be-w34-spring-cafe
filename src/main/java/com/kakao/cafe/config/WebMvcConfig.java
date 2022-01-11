package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/users/form").setViewName("user/form");
        registry.addViewController("/users/form-failed").setViewName("user/form-failed");
        registry.addViewController("/users/login").setViewName("user/login");
        registry.addViewController("/users/login-failed").setViewName("user/login-failed");
        registry.addViewController("/questions/form").setViewName("qna/form");
    }
}
