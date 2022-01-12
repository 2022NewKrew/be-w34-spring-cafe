package com.example.kakaocafe.core.config;

import com.example.kakaocafe.core.config.interceptor.AuthInterceptor;
import com.example.kakaocafe.core.config.interceptor.LoginInterceptor;
import com.example.kakaocafe.core.config.resolver.UpdateUserFormResolver;
import com.example.kakaocafe.core.config.resolver.WritePostFormResolver;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UpdateUserFormResolver());
        resolvers.add(new WritePostFormResolver());
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(URLPath.LOGIN.getPath());

        registry.addInterceptor(new AuthInterceptor())
                .addPathPatterns(URLPath.SHOW_USER_UPDATE_FORM.getPath())
                .addPathPatterns(URLPath.LOGOUT.getPath())
                .addPathPatterns(URLPath.SHOW_POST_FORM.getPath());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);

        registry.addViewController(URLPath.SHOW_SIGN_UP_FROM.getPath())
                .setViewName(ViewPath.SIGN_UP.getPath());
        registry.addViewController(URLPath.SHOW_LOGIN_FROM.getPath())
                .setViewName(ViewPath.LOGIN.getPath());
        registry.addViewController(URLPath.SHOW_POST_FORM.getPath())
                .setViewName(ViewPath.WRITE_POST.getPath());
    }
}
