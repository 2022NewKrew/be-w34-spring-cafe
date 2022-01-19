package com.kakao.cafe.config;

import com.kakao.cafe.util.AuthInterceptor;
import com.kakao.cafe.util.AuthMyArticleInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    private final AuthInterceptor authInterceptor;
    private final AuthMyArticleInterceptor authMyArticleInterceptor;

    public WebMvcConfig(AuthInterceptor authInterceptor, AuthMyArticleInterceptor authMyArticleInterceptor) {
        this.authInterceptor = authInterceptor;
        this.authMyArticleInterceptor = authMyArticleInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns().order(1);

        registry.addInterceptor(authMyArticleInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns().order(2);

    }
}
