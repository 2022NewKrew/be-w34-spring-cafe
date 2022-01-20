package com.kakao.cafe.config;

import com.kakao.cafe.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        registry.addViewController("/users/update").setViewName("updateform");
        registry.addViewController("/users/signup").setViewName("form");
        registry.addViewController("/users/login").setViewName("loginform");
        registry.addViewController("/users/success").setViewName("success");
        registry.addViewController("posts/form").setViewName("qnaform");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(authInterceptor())
                .addPathPatterns("/api/posts/**", "/posts/**", "/api/comments/**", "/auth/logout", "/users/{user-account-id}/detail")
                .excludePathPatterns("/users/list", "/api/users", "/api/auth/login", "/");
    }

    @Bean
    public AuthInterceptor authInterceptor() {
        return new AuthInterceptor();
    }
}
