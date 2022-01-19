package com.example.kakaocafe.security.config;

import com.example.kakaocafe.core.meta.URLPath;
import com.example.kakaocafe.security.filter.*;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

    private final List<URLPath> noAuthUrlPatterns = new ArrayList<>();

    @PostConstruct
    private void addNoAuthURL() {
        noAuthUrlPatterns.add(URLPath.META);
        noAuthUrlPatterns.add(URLPath.CSS);
        noAuthUrlPatterns.add(URLPath.IMAGE);

        noAuthUrlPatterns.add(URLPath.SHOW_ERROR_404);

        noAuthUrlPatterns.add(URLPath.INDEX);
        noAuthUrlPatterns.add(URLPath.SHOW_SIGN_UP_FORM);
        noAuthUrlPatterns.add(URLPath.SHOW_SIGN_UP_SUCCESS);
        noAuthUrlPatterns.add(URLPath.SIGN_UP);
        noAuthUrlPatterns.add(URLPath.SHOW_LOGIN_FORM);
        noAuthUrlPatterns.add(URLPath.LOGIN);
    }

    @Bean
    public FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilterRegistrationBean() {
        FilterRegistrationBean<ExceptionHandlerFilter> exceptionHandlerFilterRegistrationBean = new FilterRegistrationBean<>();
        exceptionHandlerFilterRegistrationBean.setFilter(new ExceptionHandlerFilter());
        exceptionHandlerFilterRegistrationBean.addUrlPatterns("/*");
        exceptionHandlerFilterRegistrationBean.setOrder(1);

        return exceptionHandlerFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<SessionManageFilter> sessionManageFilterRegistrationBean() {
        FilterRegistrationBean<SessionManageFilter> sessionManageFilterRegistrationBean = new FilterRegistrationBean<>();
        sessionManageFilterRegistrationBean.setFilter(new SessionManageFilter());
        sessionManageFilterRegistrationBean.addUrlPatterns("/*");
        sessionManageFilterRegistrationBean.setOrder(2);

        return sessionManageFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> authenticationFilterRegistrationBean = new FilterRegistrationBean<>();
        authenticationFilterRegistrationBean.setFilter(new AuthenticationFilter(noAuthUrlPatterns));
        authenticationFilterRegistrationBean.addUrlPatterns("/*");
        authenticationFilterRegistrationBean.setOrder(3);

        return authenticationFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CsrfGenerateFilter> csrfGenerateFilterRegistrationBean() {
        FilterRegistrationBean<CsrfGenerateFilter> csrfGenerateFilterRegistrationBean = new FilterRegistrationBean<>();
        csrfGenerateFilterRegistrationBean.setFilter(new CsrfGenerateFilter());
        csrfGenerateFilterRegistrationBean.addUrlPatterns("/*");
        csrfGenerateFilterRegistrationBean.setOrder(4);

        return csrfGenerateFilterRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<CsrfVerificationFilter> csrfVerificationFilterRegistrationBean() {
        FilterRegistrationBean<CsrfVerificationFilter> csrfVerificationFilterRegistrationBean = new FilterRegistrationBean<>();
        csrfVerificationFilterRegistrationBean.setFilter(new CsrfVerificationFilter());
        csrfVerificationFilterRegistrationBean.addUrlPatterns("/*");
        csrfVerificationFilterRegistrationBean.setOrder(5);

        return csrfVerificationFilterRegistrationBean;
    }
}
