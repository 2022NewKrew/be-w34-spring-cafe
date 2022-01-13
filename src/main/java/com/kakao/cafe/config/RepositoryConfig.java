package com.kakao.cafe.config;

import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.article.repository.JdbcArticleRepository;
import com.kakao.cafe.user.repository.JdbcUserRepository;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class RepositoryConfig {
    // 어떤 repository 구현체를 사용할지 결정하는 설정파일

    private final JdbcUserRepository jdbcUserRepository;
    private final JdbcArticleRepository jdbcArticleRepository;

    @Bean
    public UserRepository userRepository() {
        return jdbcUserRepository;
    }

    @Bean
    public ArticleRepository articleRepository() {
        return jdbcArticleRepository;
    }
}
