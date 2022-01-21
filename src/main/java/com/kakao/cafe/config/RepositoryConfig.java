package com.kakao.cafe.config;

import com.kakao.cafe.article.repository.ArticleRepository;
import com.kakao.cafe.article.repository.JdbcArticleRepository;
import com.kakao.cafe.reply.repository.JdbcReplyRepository;
import com.kakao.cafe.reply.repository.ReplyRepository;
import com.kakao.cafe.user.repository.JdbcUserRepository;
import com.kakao.cafe.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@RequiredArgsConstructor
@Configuration
public class RepositoryConfig {
    // 어떤 repository 구현체를 사용할지 결정하는 설정파일

    @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcUserRepository(jdbcTemplate);
    }

    @Bean
    public ArticleRepository articleRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcArticleRepository(jdbcTemplate);
    }

    @Bean
    public ReplyRepository replyRepository(JdbcTemplate jdbcTemplate) {
        return new JdbcReplyRepository(jdbcTemplate);
    }
}
