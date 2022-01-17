package com.kakao.cafe.config;

import com.kakao.cafe.repository.JdbcArticleRepository;
import com.kakao.cafe.repository.JdbcUserRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public JdbcUserRepository userRepository() {
        return new JdbcUserRepository(dataSource);
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

    @Bean
    public JdbcArticleRepository articleRepository() {
        return new JdbcArticleRepository(dataSource);
    }
}
