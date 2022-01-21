package com.kakao.cafe.config;

import com.kakao.cafe.controller.interceptor.AuthenticatedInterceptor;
import com.kakao.cafe.controller.interceptor.LoggingInterceptor;
import com.kakao.cafe.controller.interceptor.SessionUserArgumentResolver;
import com.kakao.cafe.util.PageArgumentResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/users/form").setViewName("user/form");
        registry.addViewController("/users/login").setViewName("user/login");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new SessionUserArgumentResolver());
        resolvers.add(new PageArgumentResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoggingInterceptor());
        registry.addInterceptor(new AuthenticatedInterceptor());
    }
}
