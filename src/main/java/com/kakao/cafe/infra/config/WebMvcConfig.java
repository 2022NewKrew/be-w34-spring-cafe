package com.kakao.cafe.infra.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/user/signup").setViewName("user/signup");
        registry.addViewController("/user/login-form").setViewName("user/login-form");
        registry.addViewController("/article/form").setViewName("article/form");
        registry.addRedirectViewController("/", "/article");
    }
}
