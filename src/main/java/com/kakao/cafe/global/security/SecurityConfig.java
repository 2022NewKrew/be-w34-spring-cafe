package com.kakao.cafe.global.security;

import com.kakao.cafe.global.meta.RequestPath;
import com.kakao.cafe.global.security.filter.SessionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

    // 로그인 session을 요구하지 않을 request white list
    private final List<RequestPath> noAuthUrlPatterns = new ArrayList<>();

    @PostConstruct
    private void addNoAuthUrl() {
        noAuthUrlPatterns.add(RequestPath.STATIC_FAVICON);
        noAuthUrlPatterns.add(RequestPath.STATIC_CSS);
        noAuthUrlPatterns.add(RequestPath.STATIC_FONTS);
        noAuthUrlPatterns.add(RequestPath.STATIC_IMAGES);
        noAuthUrlPatterns.add(RequestPath.STATIC_JS);

        noAuthUrlPatterns.add(RequestPath.HOME);
        noAuthUrlPatterns.add(RequestPath.ARTICLE_LIST);
        noAuthUrlPatterns.add(RequestPath.USER_JOIN);
        noAuthUrlPatterns.add(RequestPath.USER_LOGIN);

        noAuthUrlPatterns.add(RequestPath.USER_JOIN_FORM);
        noAuthUrlPatterns.add(RequestPath.USER_LOGIN_FORM);
        noAuthUrlPatterns.add(RequestPath.USER_LOGIN_FORM_FAILED);

    }

    // session(로그인) filter
    @Bean
    public FilterRegistrationBean<SessionFilter> sessionFilterRegister() {
        FilterRegistrationBean<SessionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new SessionFilter(noAuthUrlPatterns));
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        registrationBean.setName("session-filter");
        return registrationBean;
    }
}
