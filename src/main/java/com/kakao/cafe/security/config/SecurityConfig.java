package com.kakao.cafe.security.config;

import com.kakao.cafe.common.meta.URLPath;
import com.kakao.cafe.security.filter.AuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {
    private final List<String> noAuthUrlPatterns = new ArrayList<>();

    @PostConstruct
    private void addNoAuthURL() {
        noAuthUrlPatterns.add("/favicon.ico");
        noAuthUrlPatterns.add("/css/(.*)");
        noAuthUrlPatterns.add("/images/(.*)");

        noAuthUrlPatterns.add("/error/(.*)");
        noAuthUrlPatterns.add("/index");

        noAuthUrlPatterns.add(URLPath.INDEX.getPath());
        noAuthUrlPatterns.add(URLPath.LOGIN_FORM.getPath());
        noAuthUrlPatterns.add(URLPath.LOGIN_FAILED.getPath());
        noAuthUrlPatterns.add(URLPath.SHOW_SIGN_UP_FORM.getPath());
        noAuthUrlPatterns.add(URLPath.SHOW_USERS.getPath());
    }

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuthenticationFilter> authenticationFilterFilterRegistrationBean = new FilterRegistrationBean<>();
        authenticationFilterFilterRegistrationBean.setFilter(new AuthenticationFilter(noAuthUrlPatterns));
        authenticationFilterFilterRegistrationBean.addUrlPatterns("/*");
        authenticationFilterFilterRegistrationBean.setOrder(1);

        return authenticationFilterFilterRegistrationBean;
    }
}
