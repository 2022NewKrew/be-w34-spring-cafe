package com.kakao.cafe.config;

import com.kakao.cafe.domain.post.impl.InMemoryPostRepository;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.domain.user.impl.InMemoryUserRepository;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/users");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/posts/new").setViewName("post_new");
    }

    @Bean
    public PostRepository getPostRepositoryBean() {
        return new InMemoryPostRepository();
    }

    @Bean
    public UserRepository getUserRepositoryBean() {
        return new InMemoryUserRepository();
    }
}
