package com.kakao.cafe.app.config;

import com.kakao.cafe.app.controller.interceptor.AuthInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(HttpMethod.GET))
                .addPathPatterns("/articles/form");
        registry.addInterceptor(new AuthInterceptor(HttpMethod.GET, HttpMethod.DELETE))
                .addPathPatterns("/articles/*");
    }
}
