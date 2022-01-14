package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Ordered.LOWEST_PRECEDENCE 로 하면 매핑 우선순위가 밀려서 "/" 가 "index.html" 로 연결된다.
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addRedirectViewController("/", "users");
        registry.addViewController("/user/signup").setViewName("user/form");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("classpath:/static/");
    }
}
