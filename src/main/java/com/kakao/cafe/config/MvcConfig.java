package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/member/form").setViewName("member/form");
        registry.addViewController("/member/list").setViewName("member/list");
        registry.addViewController("/member/login").setViewName("member/login");
        registry.addViewController("/qna/form").setViewName("qna/form");
    }
}
