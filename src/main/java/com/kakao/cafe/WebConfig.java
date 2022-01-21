package com.kakao.cafe;

import com.kakao.cafe.interceptor.LoggingInterceptor;
import com.kakao.cafe.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor())
                .order(1)
                .addPathPatterns("/**");

        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/users/loginForm", "/users/login", "/users/signUpForm", "/css/**", "/fonts/**",
                        "/images/**", "/js/**", "/error", "/*.ico");


    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/users/signUpForm").setViewName("user/signUpForm");
        registry.addViewController("/users/loginForm").setViewName("user/login");
        registry.addViewController("/articles/form").setViewName("article/form");
    }
}
