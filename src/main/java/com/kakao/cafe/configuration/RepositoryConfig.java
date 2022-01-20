package com.kakao.cafe.configuration;

import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.JdbcArticleRepository;
import com.kakao.cafe.repository.article.mapper.ArticleRowMapper;
import com.kakao.cafe.repository.user.JdbcUserRepository;
import com.kakao.cafe.repository.user.UserRepository;

import com.kakao.cafe.repository.user.mapper.UserRowMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        return new JdbcUserRepository(jdbcTemplate, userRowMapper);
    }

    @Bean
    public ArticleRepository articleRepository(JdbcTemplate jdbcTemplate, ArticleRowMapper articleRowMapper) {
        return new JdbcArticleRepository(jdbcTemplate, articleRowMapper);
    }
}
