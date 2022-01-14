package com.kakao.cafe.config;

import com.kakao.cafe.domain.post.PostMapper;
import com.kakao.cafe.domain.post.impl.InMemoryPostRepository;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.domain.post.impl.JdbcTemplatePostRepository;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.domain.user.impl.JdbcTemplateUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/users");
        registry.addViewController("/signup").setViewName("signup");
        registry.addViewController("/write").setViewName("post_new");
    }

    @Bean
    public PostRepository getPostRepositoryBean(DataSource dataSource, PostMapper postMapper) {
        return new JdbcTemplatePostRepository(dataSource, postMapper);
    }

    @Bean
    public UserRepository getUserRepositoryBean(DataSource dataSource, UserMapper userMapper) {
        return new JdbcTemplateUserRepository(dataSource, userMapper);
    }
}
