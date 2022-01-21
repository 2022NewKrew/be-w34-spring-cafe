package com.kakao.cafe.core.config;

import com.kakao.cafe.core.config.interceptor.LoginInterceptor;
import com.kakao.cafe.core.config.resolver.ArticleCreateFormResolver;
import com.kakao.cafe.core.config.resolver.ArticleUpdateFormResolver;
import com.kakao.cafe.core.config.resolver.CommentCreateFormResolver;
import com.kakao.cafe.core.config.resolver.UserUpdateFormResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        final LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns(loginInterceptor.includePattern)
                .excludePathPatterns(loginInterceptor.excludePattern);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new UserUpdateFormResolver());
        resolvers.add(new ArticleCreateFormResolver());
        resolvers.add(new ArticleUpdateFormResolver());
        resolvers.add(new CommentCreateFormResolver());
    }
}
