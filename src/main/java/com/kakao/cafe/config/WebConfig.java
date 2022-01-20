package com.kakao.cafe.config;

import com.kakao.cafe.web.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .excludePathPatterns("/user/login",
                        "/user/form",
                        "/user",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/fonts/**",
                        "/favicon.io");
    }
}
