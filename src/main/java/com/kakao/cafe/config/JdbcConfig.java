package com.kakao.cafe.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class JdbcConfig {

    @Bean
    @Profile("local")
    public DataSource dataSource() {
        System.out.println("------local------");
        System.out.println();
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("kakaodb")
                .addScript("classpath:/h2-sql/settings.sql")
                .addScript("classpath:/h2-sql/tables.sql")
                .build();
    }

    @Bean
    @Profile("dev")
    public DataSource mysqlDataSource() {
        System.out.println("------dev------");
        System.out.println();
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/kakaodb");
        dataSource.setUsername("kakao");
        dataSource.setPassword("kakao");

        return dataSource;
    }
}
