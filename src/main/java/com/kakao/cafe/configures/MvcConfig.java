package com.kakao.cafe.configures;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/users/form").setViewName("user/form");
        registry.addViewController("/users/list").setViewName("user/list");
        registry.addViewController("/articles/form").setViewName("qna/form");
        registry.addViewController("/articles/list").setViewName("index");
        registry.addViewController("/articles/show").setViewName("qna/show");
    }
}
