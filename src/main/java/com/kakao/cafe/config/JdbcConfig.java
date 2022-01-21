package com.kakao.cafe.config;

import javax.sql.DataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class JdbcConfig {

    private final Logger logger = LoggerFactory.getLogger(JdbcConfig.class);

    @Bean
    @Profile("local")
    public DataSource dataSource() {
        logger.debug("use h2 Database");
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("kakaodb")
                .addScript("classpath:/db/h2-sql/settings.sql")
                .addScript("classpath:/db/h2-sql/tables.sql")
                .build();
    }

    @Bean
    @Profile("dev")
    public DataSource mysqlDataSource() {
        logger.debug("use mysql database");

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://mysql:3306/kakaodb");
        dataSource.setUsername("kakao");
        dataSource.setPassword("kakao");

        return dataSource;
    }
}
