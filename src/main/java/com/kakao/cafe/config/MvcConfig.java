package com.kakao.cafe.config;

import com.kakao.cafe.adapter.in.presentation.SessionCheckInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/users/form").setViewName("user/form");
        registry.addViewController("/users/error").setViewName("user/form");
        registry.addViewController("/articles/form").setViewName("article/form");
        registry.addViewController("/articles/error").setViewName("article/form");
        registry.addViewController("/user/login").setViewName("user/login");
        registry.addViewController("login/error").setViewName("user/login");
    }

    @Bean
    public SessionCheckInterceptor sessionCheckInterceptor() {
        return new SessionCheckInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionCheckInterceptor())
                .addPathPatterns("/users/*/form")
                .addPathPatterns("/articles/**");
    }
}
