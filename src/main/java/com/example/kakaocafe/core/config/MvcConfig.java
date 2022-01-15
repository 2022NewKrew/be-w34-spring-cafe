package com.example.kakaocafe.core.config;

import com.example.kakaocafe.core.config.resolver.UpdateUserFormResolver;
import com.example.kakaocafe.core.config.resolver.WriteCommentFormResolver;
import com.example.kakaocafe.core.config.resolver.WritePostFormResolver;
import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.core.meta.ViewPath;
import com.example.kakaocafe.security.filter.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class MvcConfig implements WebMvcConfigurer {

    private final SessionManageFilter sessionManageFilter;
    private final CsrfGenerateFilter csrfGenerateFilter;
    private final CsrfVerificationFilter csrfVerificationFilter;
    private final AuthenticationFilter authenticationFilter;

    @Bean
    public FilterRegistrationBean<SessionManageFilter> sessionManageFilterRegistrationBean() {
        FilterRegistrationBean<SessionManageFilter> sessionManageFilterRegistrationBean = new FilterRegistrationBean<>();
        sessionManageFilterRegistrationBean.setFilter(sessionManageFilter);
        sessionManageFilterRegistrationBean.addUrlPatterns("/*");
        sessionManageFilterRegistrationBean.setOrder(1);

        return sessionManageFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistrationBean = new FilterRegistrationBean<>();
        authenticationFilterRegistrationBean.setFilter(authenticationFilter);
        authenticationFilterRegistrationBean.addUrlPatterns("/*");
        authenticationFilterRegistrationBean.setOrder(2);

        return authenticationFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CsrfGenerateFilter> csrfGenerateFilterRegistrationBean() {
        FilterRegistrationBean<CsrfGenerateFilter> csrfGenerateFilterRegistrationBean = new FilterRegistrationBean<>();
        csrfGenerateFilterRegistrationBean.setFilter(csrfGenerateFilter);
        csrfGenerateFilterRegistrationBean.addUrlPatterns("/*");
        csrfGenerateFilterRegistrationBean.setOrder(3);

        return csrfGenerateFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CsrfVerificationFilter> csrfVerificationFilterRegistrationBean() {
        FilterRegistrationBean<CsrfVerificationFilter> csrfVerificationFilterRegistrationBean = new FilterRegistrationBean<>();
        csrfVerificationFilterRegistrationBean.setFilter(csrfVerificationFilter);
        csrfVerificationFilterRegistrationBean.addUrlPatterns("/*");
        csrfVerificationFilterRegistrationBean.setOrder(4);

        return csrfVerificationFilterRegistrationBean;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UpdateUserFormResolver());
        resolvers.add(new WritePostFormResolver());
        resolvers.add(new WriteCommentFormResolver());
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        registry.addViewController(URLPath.SHOW_SIGN_UP_FROM.getPath())
                .setViewName(ViewPath.SIGN_UP);
        registry.addViewController(URLPath.SHOW_LOGIN_FROM.getPath())
                .setViewName(ViewPath.LOGIN);
        registry.addViewController(URLPath.SHOW_POST_FORM.getPath())
                .setViewName(ViewPath.WRITE_POST);
        registry.addViewController(URLPath.SHOW_LOGIN_FAILED.getPath())
                .setViewName(ViewPath.LOGIN_FAILED);
    }
}
