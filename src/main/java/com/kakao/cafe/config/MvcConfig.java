package com.kakao.cafe.config;

import com.kakao.cafe.common.AuthHandlerInterceptor;
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
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/").setViewName("posts");
        registry.addViewController("/users/form").setViewName("users/form");
        registry.addViewController("/posts/form").setViewName("posts/form");
    }

    @Bean
    public AuthHandlerInterceptor authHandlerInterceptor() {
        return new AuthHandlerInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/css/**", "/fonts/**", "/images/**", "/js/**")
                .excludePathPatterns("/users", "/users/form", "/users/login")
                .excludePathPatterns("/", "/posts");
    }
}
