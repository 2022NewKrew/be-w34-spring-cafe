package com.kakao.cafe.configures;

import com.kakao.cafe.configures.web.ArticleRequestHandlerMethodArgumentResolver;
import com.kakao.cafe.configures.web.ReplyRequestHandlerMethodArgumentResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final HandlerInterceptor loginInterceptor;

    public WebConfig(HandlerInterceptor loginInterceptor) {
        this.loginInterceptor = loginInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(Arrays.asList("/qna/**"))
                .excludePathPatterns();
    }

    @Bean
    public ArticleRequestHandlerMethodArgumentResolver ArticleRequestHandlerMethodArgumentResolver() {
        return new ArticleRequestHandlerMethodArgumentResolver();
    }

    @Bean
    public ReplyRequestHandlerMethodArgumentResolver ReplyRequestHandlerMethodArgumentResolver() {
        return new ReplyRequestHandlerMethodArgumentResolver();
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(ArticleRequestHandlerMethodArgumentResolver());
        argumentResolvers.add(ReplyRequestHandlerMethodArgumentResolver());
    }

}
