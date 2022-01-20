package com.kakao.cafe.config;

import com.kakao.cafe.repository.JdbcArticleRepository;
import com.kakao.cafe.repository.JdbcReplyRepository;
import com.kakao.cafe.repository.JdbcUserRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ReplyService;
import com.kakao.cafe.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@RequiredArgsConstructor
@Configuration
public class SpringConfig {
    private final DataSource dataSource;

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

    @Bean
    public ReplyService replyService() {
        return new ReplyService(replyRepository());
    }

    @Bean
    public JdbcReplyRepository replyRepository() {
        return new JdbcReplyRepository(dataSource);
    }
}
