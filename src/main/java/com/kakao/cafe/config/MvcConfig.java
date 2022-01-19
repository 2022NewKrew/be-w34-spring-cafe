package com.kakao.cafe.config;

import com.kakao.cafe.web.AuthHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    private final AuthHandlerInterceptor authHandlerInterceptor;

    public MvcConfig(AuthHandlerInterceptor authHandlerInterceptor) {
        this.authHandlerInterceptor = authHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authHandlerInterceptor)
                .addPathPatterns("/users/**")
                .addPathPatterns("/article/**")
                .excludePathPatterns("/users");

    }
}
