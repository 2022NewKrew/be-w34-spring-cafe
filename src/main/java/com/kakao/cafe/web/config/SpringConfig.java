package com.kakao.cafe.web.config;

import com.kakao.cafe.web.repository.article.ArticleRepository;
import com.kakao.cafe.web.repository.article.MemoryArticleRepository;
import com.kakao.cafe.web.repository.user.JdbcTemplateUserRepository;
import com.kakao.cafe.web.repository.user.MemoryUserRepository;
import com.kakao.cafe.web.repository.user.UserRepository;
import com.kakao.cafe.web.service.ArticleService;
import com.kakao.cafe.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
//        return new MemoryUserRepository();
        return new JdbcTemplateUserRepository(dataSource);
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
