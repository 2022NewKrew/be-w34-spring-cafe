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

        registry.addViewController("/").setViewName("index");
        registry.addViewController("/user/signup").setViewName("user/signup");
        registry.addViewController("/user/login").setViewName("user/login");
        registry.addViewController("/user/login_failed").setViewName("user/login_failed");
        registry.addViewController("/user/list").setViewName("user/list");
        registry.addViewController("/user/profile").setViewName("user/profile");
        registry.addViewController("/post/form").setViewName("post/form");
        registry.addViewController("/post/show").setViewName("post/show");
    }
}
