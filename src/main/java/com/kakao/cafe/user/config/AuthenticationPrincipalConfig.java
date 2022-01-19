package com.kakao.cafe.user.config;

import com.kakao.cafe.user.repository.SessionMapRepository;
import com.kakao.cafe.user.repository.SessionRepository;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationPrincipalConfig implements WebMvcConfigurer {

    private final SessionRepository sessionRepository;

    public AuthenticationPrincipalConfig(SessionMapRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AuthInterceptor(sessionRepository))
            .addPathPatterns("/questions/{**")
            .addPathPatterns("/questions");
    }
}
