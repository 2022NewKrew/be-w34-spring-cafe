package com.kakao.cafe.web.config;

import com.kakao.cafe.web.repository.article.ArticleRepository;
import com.kakao.cafe.web.repository.article.MemoryArticleRepository;
import com.kakao.cafe.web.repository.user.MemoryUserRepository;
import com.kakao.cafe.web.repository.user.UserRepository;
import com.kakao.cafe.web.service.ArticleService;
import com.kakao.cafe.web.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new MemoryArticleRepository();
    }
}
