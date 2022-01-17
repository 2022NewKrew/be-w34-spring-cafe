package com.kakao.cafe.config;

import com.kakao.cafe.common.interceptor.LoginInterceptor;
import com.kakao.cafe.common.interceptor.UserUpdateInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/css/**","/users/**", "/js/**","/questions/**","/img/**");

        registry.addInterceptor(new UserUpdateInterceptor())
                .order(1)
                .addPathPatterns("/users/update/{id}");
    }
}
