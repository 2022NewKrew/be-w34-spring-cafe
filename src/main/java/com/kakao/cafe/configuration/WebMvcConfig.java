package com.kakao.cafe.configuration;

import com.kakao.cafe.interceptor.ArticleSameUserInterceptor;
import com.kakao.cafe.interceptor.ReplySameUserInterceptor;
import com.kakao.cafe.interceptor.SessionInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    private final SessionInterceptor sessionInterceptor;
    private final ArticleSameUserInterceptor articleSameUserInterceptor;
    private final ReplySameUserInterceptor replySameUserInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns(sessionInterceptor.loginEssential);

        registry.addInterceptor(articleSameUserInterceptor)
                .addPathPatterns(articleSameUserInterceptor.sameUserEssential);

        registry.addInterceptor(replySameUserInterceptor)
                .addPathPatterns(replySameUserInterceptor.sameUserEssential);
    }
}
