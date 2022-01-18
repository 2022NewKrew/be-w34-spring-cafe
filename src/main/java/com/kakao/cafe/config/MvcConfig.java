package com.kakao.cafe.config;

import com.kakao.cafe.domain.post.PostMapper;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.domain.post.impl.JdbcTemplatePostRepository;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.domain.user.impl.JdbcTemplateUserRepository;
import com.kakao.cafe.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Bean
    public PostRepository getPostRepositoryBean(DataSource dataSource, PostMapper postMapper) {
        return new JdbcTemplatePostRepository(dataSource, postMapper);
    }

    @Bean
    public UserRepository getUserRepositoryBean(DataSource dataSource, UserMapper userMapper) {
        return new JdbcTemplateUserRepository(dataSource, userMapper);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/users");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/update").setViewName("update");
        registry.addViewController("/write").setViewName("post_new");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns(LoginInterceptor.loginNeeded)
                .excludePathPatterns(LoginInterceptor.loginNotNeeded);
    }
}
