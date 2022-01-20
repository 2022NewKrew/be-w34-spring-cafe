package com.kakao.cafe.config;

import com.kakao.cafe.controller.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer{
    private final LoginInterceptor loginInterceptor;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/form").setViewName("user/form");
        registry.addViewController("/users/form").setViewName("user/form");
//        registry.addViewController("/login").setViewName("user/login");
        registry.addViewController("/users/login").setViewName("user/login");
        registry.addViewController("/login-failed").setViewName("user/login-failed");
//        registry.addViewController("/articles/form").setViewName("article/form");
        registry.addViewController("/error").setViewName("error");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .excludePathPatterns("/js/*", "/css/*", "/fonts/*", "/*.ico");
    }
}
