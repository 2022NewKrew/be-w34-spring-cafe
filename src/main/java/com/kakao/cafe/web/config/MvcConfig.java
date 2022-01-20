package com.kakao.cafe.web.config;

import com.kakao.cafe.web.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class MvcConfig implements WebMvcConfigurer {


    private final LoginInterceptor loginInterceptor;

    public MvcConfig(LoginInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/article/createForm")
                .addPathPatterns("/articles/**")
                .addPathPatterns("/users/*/form")
                .addPathPatterns("/users/*/update");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/joinForm").setViewName("user/joinForm");
        registry.addViewController("/user/loginForm").setViewName("user/loginForm");
    }

}
