package com.kakao.cafe.config;

import com.kakao.cafe.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    private final List<String> noLoginList;

    public MvcConfig() {
        noLoginList = new ArrayList<>();
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController("/user/list.html").setViewName("/user/list");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        noLoginList.add("/");
        noLoginList.add("/index");
        noLoginList.add("/user/login");
        noLoginList.add("/user/form");
        noLoginList.add("/css/**");
        noLoginList.add("/js/**");
        noLoginList.add("/fonts/**");
        noLoginList.add("/images/**");
        noLoginList.add("/favicon.ico");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns(noLoginList);
    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        noLoginList.add("/");
//        noLoginList.add("/index");
//        registry.addInterceptor(new LoginInterceptor());
//    }
}
