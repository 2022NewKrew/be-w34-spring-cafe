package com.kakao.cafe.config;

import com.kakao.cafe.repository.*;
import com.kakao.cafe.service.*;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public ReplyRepository replyDao() {
        return new ReplyDao(jdbcTemplate());
    }

    @Bean
    public ReplyService replyService() { return new ReplyServiceImpl(replyDao()); }

    @Bean
    public ArticleService articleService() {
        return new ArticleServiceImpl(articleDao(), userDao());
    }

    @Bean
    public ArticleRepository articleDao() {
        return new ArticleDao(jdbcTemplate());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userDao());
    }

    @Bean
    public UserRepository userDao() {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        /*
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test")
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();

         */
        return DataSourceBuilder.create()
                .url("jdbc:mysql://test-cafe-ed.ay1.krane.9rum.cc:3306/cafedb") // ?useUnicode=yes&characterEncoding=UTF-8
                .username("ed2")
                .password("Shim7414!")
                .build();
    }
}
