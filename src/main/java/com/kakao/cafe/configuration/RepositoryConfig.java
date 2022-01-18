package com.kakao.cafe.configuration;

import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.JdbcArticleRepository;
import com.kakao.cafe.repository.user.JdbcUserRepository;
import com.kakao.cafe.repository.user.UserRepository;

import com.kakao.cafe.repository.user.mapper.UserRowMapper;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate, UserRowMapper userRowMapper) {
        return new JdbcUserRepository(jdbcTemplate, userRowMapper);
    }

    @Bean
    public ArticleRepository articleRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcArticleRepository(jdbcTemplate);
    }
}
