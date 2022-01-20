package com.kakao.cafe.config;

import com.kakao.cafe.qna.article.repository.ArticleRepository;
import com.kakao.cafe.qna.article.repository.JdbcArticleRepository;
import com.kakao.cafe.user.JdbcUserRepository;
import com.kakao.cafe.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * Created by melodist
 * Date: 2022-01-10 010
 * Time: 오후 1:55
 */
@Configuration
public class RepoConfig {

    private final DataSource dataSource;

    @Autowired
    public RepoConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserRepository userRepository() {
        return new JdbcUserRepository(dataSource);
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new JdbcArticleRepository(dataSource);
    }}
