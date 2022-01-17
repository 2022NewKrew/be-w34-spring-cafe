package com.kakao.cafe.config;

import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.H2ArticleRepository;
import com.kakao.cafe.repository.user.H2UserRepository;
import com.kakao.cafe.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class JdbcSpringConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("kakaodb")
                .addScript("classpath:schema.sql")
                .addScript("classpath:data.sql").build();
    }

    @Bean
    public UserRepository getUserRepository(JdbcTemplate jdbcTemplate) {
        return new H2UserRepository(jdbcTemplate);
    }

    @Bean
    public ArticleRepository getArticleRepository(JdbcTemplate jdbcTemplate) {
        return new H2ArticleRepository(jdbcTemplate);
    }
}
