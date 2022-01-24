package com.kakao.cafe.config;

import com.kakao.cafe.repository.JdbcTemplateArticleRepository;
import com.kakao.cafe.repository.JdbcTemplateReplyRepository;
import com.kakao.cafe.repository.JdbcTemplateUserRepository;
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
    public JdbcTemplateUserRepository userRepository() {
        return new JdbcTemplateUserRepository(dataSource);
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

    @Bean
    public JdbcTemplateArticleRepository articleRepository() {
        return new JdbcTemplateArticleRepository(dataSource);
    }

    @Bean
    public ReplyService replyService() {
        return new ReplyService(replyRepository());
    }

    @Bean
    public JdbcTemplateReplyRepository replyRepository() {
        return new JdbcTemplateReplyRepository(dataSource);
    }
}
