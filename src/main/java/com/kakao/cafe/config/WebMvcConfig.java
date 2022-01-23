package com.kakao.cafe.config;

import com.kakao.cafe.interceptor.LoginCheckInterceptor;
import com.kakao.cafe.interceptor.PermissionCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginCheckInterceptor loginCheckInterceptor = new LoginCheckInterceptor();
        registry.addInterceptor(loginCheckInterceptor)
                .addPathPatterns(loginCheckInterceptor.getCheckUrl());

        PermissionCheckInterceptor permissionCheckInterceptor = new PermissionCheckInterceptor();
        registry.addInterceptor(permissionCheckInterceptor)
                .addPathPatterns(permissionCheckInterceptor.getCheckUrl());
    }
}
