package com.kakao.cafe.configuration;

import com.kakao.cafe.interceptor.SessionInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        SessionInterceptor sessionInterceptor = new SessionInterceptor();
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns(sessionInterceptor.loginEssential)
                .excludePathPatterns();
    }
}
