package com.kakao.cafe.config;

import com.kakao.cafe.repository.ArticleDao;
import com.kakao.cafe.repository.UserDao;
import com.kakao.cafe.service.ArticleServiceImpl;
import com.kakao.cafe.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public ArticleServiceImpl articleService() {
        return new ArticleServiceImpl(articleDao(), userDao());
    }

    @Bean
    public ArticleDao articleDao() {
        return new ArticleDao(jdbcTemplate());
    }

    @Bean
    public UserServiceImpl userService() {
        return new UserServiceImpl(userDao());
    }

    @Bean
    public UserDao userDao() {
        return new UserDao(jdbcTemplate());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(dataSource());
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("test")
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql")
                .build();
    }
}
