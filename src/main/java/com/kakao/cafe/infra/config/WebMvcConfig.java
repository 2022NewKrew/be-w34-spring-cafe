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

        registry.addViewController("/auth/form").setViewName("user/form");
        registry.addViewController("/auth/sign-in/form").setViewName("user/login");
        registry.addViewController("/articles/form").setViewName("article/form");
    }
}
