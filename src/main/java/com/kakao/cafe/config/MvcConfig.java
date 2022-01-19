package com.kakao.cafe.config;

import com.kakao.cafe.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);

        registry.addViewController("/signup").setViewName("/user/form");
        registry.addViewController("/login").setViewName("/user/login");
        registry.addViewController("/update").setViewName("/user/update");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(LoginInterceptor.loginNeeded)
                .excludePathPatterns(LoginInterceptor.loginNotNeeded);
    }
}
