package com.kakao.cafe.config;

import com.kakao.cafe.handler.AuthHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    public static final List<String> includePatterns = List.of("/users/{id}", "/articles/**");


    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        AuthHandler authHandler = new AuthHandler();
        registry.addInterceptor(authHandler)
                .addPathPatterns(includePatterns)
                .excludePathPatterns("/");
    }
}
