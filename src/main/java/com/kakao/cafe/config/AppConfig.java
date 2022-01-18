package com.kakao.cafe.config;

import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.JdbcArticleRepository;
import com.kakao.cafe.repository.JdbcUserRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ArticleServiceImpl;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImpl;
import com.kakao.cafe.util.ArticleMapper;
import com.kakao.cafe.util.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class AppConfig {

    @Bean
    public ArticleService articleService() { return new ArticleServiceImpl(jdbcArticleRepository());}

    @Bean
    public JdbcArticleRepository jdbcArticleRepository() {
        return new JdbcArticleRepository(jdbcTemplate(), articleMapper())
    }

    @Bean
        public UserService userService() {
        return new UserServiceImpl(jdbcUserRepository());
    }

    @Bean
    public JdbcUserRepository jdbcUserRepository() {
        return new JdbcUserRepository(jdbcTemplate(), userMapper(), new SecurityConfig().passwordEncoder());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(new JdbcConfig().dataSource());
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

    @Bean
    public ArticleMapper articleMapper() {
        return new ArticleMapper();
    }

}
