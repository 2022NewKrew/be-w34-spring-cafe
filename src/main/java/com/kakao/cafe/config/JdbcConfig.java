package com.kakao.cafe.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class JdbcConfig {

    /*@Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
            .setType(EmbeddedDatabaseType.H2)
            .setName("kakaodb")
            .addScript("classpath:schema.sql")
            .addScript("classpath:data.sql").build();
    }*/
}
