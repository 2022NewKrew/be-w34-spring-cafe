package com.kakao.cafe.app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ViewConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        String[] paths = {
                "/articles/form",
                "/users/form",
                "/users/login",
                "/users/login_failed",
        };
        for (String path : paths) {
            addStaticViewController(registry, path);
        }
    }

    private void addStaticViewController(ViewControllerRegistry registry, String path) {
        registry.addViewController(path).setViewName(path.replaceAll("^/", ""));
    }
}
