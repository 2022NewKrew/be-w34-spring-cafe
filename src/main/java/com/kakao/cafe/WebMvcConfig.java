package com.kakao.cafe;

import com.kakao.cafe.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry interceptorRegistry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        interceptorRegistry.addInterceptor(loginInterceptor)
                .addPathPatterns(loginInterceptor.needLogin)
                .excludePathPatterns(loginInterceptor.notNeedLogin);
    }
}
