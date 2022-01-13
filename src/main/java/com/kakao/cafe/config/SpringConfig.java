package com.kakao.cafe.config;

import com.kakao.cafe.repository.ArticleDao;
import com.kakao.cafe.repository.UserDao;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleDao(), userDao());
    }

    @Bean
    public ArticleDao articleDao() {
        return new ArticleDao(jdbcTemplate());
    }

    @Bean
    public UserService userService() {
        return new UserService(userDao());
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
