package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/templates/", "classpath:/static/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

//        registry.addViewController("qna/form").setViewName("qna/form");
//        registry.addViewController("qna/show").setViewName("qna/show");
//
//        registry.addViewController("user/form").setViewName("user/form");
//        registry.addViewController("user/list").setViewName("user/list");
//        registry.addViewController("user/login").setViewName("user/login");
//        registry.addViewController("user/login_failed").setViewName("user/login_failed");
//        registry.addViewController("user/profile").setViewName("user/profile");

    }
}
