package com.kakao.cafe.config;

import com.kakao.cafe.repository.*;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ArticleServiceImpl;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImpl;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public ReplyRepository replyDao() {
        return new ReplyDao(jdbcTemplate());
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleServiceImpl(articleDao(), userDao(), replyDao());
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

        /*
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://test-cafe-ed.ay1.krane.9rum.cc:3306/cafedb");
        dataSource.setUsername("ed2");
        dataSource.setPassword("Shim7414!");

        return dataSource;
         */

        return DataSourceBuilder.create()
                .url("jdbc:mysql://test-cafe-ed.ay1.krane.9rum.cc:3306/cafedb") // ?useUnicode=yes&characterEncoding=UTF-8
                .username("ed2")
                .password("Shim7414!")
                .build();
    }
}
