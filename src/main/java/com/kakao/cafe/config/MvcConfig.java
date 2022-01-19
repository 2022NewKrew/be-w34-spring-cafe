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
        registry.addRedirectViewController("/", "/users");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/update").setViewName("update");
        registry.addViewController("/write").setViewName("post_new");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(LoginInterceptor.loginNeeded)
                .excludePathPatterns(LoginInterceptor.loginNotNeeded);
    }
}
