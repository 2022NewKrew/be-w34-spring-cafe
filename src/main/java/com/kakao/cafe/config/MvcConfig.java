package com.kakao.cafe.config;

import com.kakao.cafe.interceptor.AuthenticationInterceptor;
import com.kakao.cafe.interceptor.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final AuthenticationInterceptor authenticationInterceptor;
    private final AuthorizationInterceptor authorizationInterceptor;
    private final List<String> AUTHENTICATION_URL = new ArrayList<>() {{
        add("/**");
    }};
    private final List<String> AUTHORIZATION_URL = new ArrayList<>() {{
        add("/**");
    }};
    private final List<String> STATIC_URL = new ArrayList<>() {{
        add("/css/**");
        add("/fonts/**");
        add("/images/**");
        add("/js/**");
        add("/favicon.ico");
    }};

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        WebMvcConfigurer.super.addViewControllers(registry);
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/users/form").setViewName("/users/form");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(authenticationInterceptor)
                .addPathPatterns(AUTHENTICATION_URL)
                .excludePathPatterns(STATIC_URL);

        registry.addInterceptor(authorizationInterceptor)
                .addPathPatterns(AUTHORIZATION_URL)
                .excludePathPatterns(STATIC_URL);
    }
}
