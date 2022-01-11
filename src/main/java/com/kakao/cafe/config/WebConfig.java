package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 5:38
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController("/user/form").setViewName("/user/form");
        registry.addViewController("/user/login").setViewName("/user/login");
        registry.addViewController("/qna/form").setViewName("/qna/form");
    }
}
