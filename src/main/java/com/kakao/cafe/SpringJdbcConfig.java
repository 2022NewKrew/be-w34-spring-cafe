package com.kakao.cafe;

import com.kakao.cafe.repository.*;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.web.context.request.RequestContextListener;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

@Configuration
public class SpringJdbcConfig {

    private final DataSource dataSource;

    public SpringJdbcConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public ArticleService articleService(){
        return new ArticleService(articleRepository(), replyRepository());
    }

    @Bean
    public ArticleRepository articleRepository(){
        return new SpringJdbcMemoryArticle(dataSource);
    }

    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new SpringJdbcMemoryUser(dataSource);
    }

    @Bean
    public ReplyRepository replyRepository() {return new SpringJdbcMemoryReply(dataSource);};



}
