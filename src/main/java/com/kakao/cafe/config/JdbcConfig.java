package com.kakao.cafe.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

// mysql 전환으로 미사용
//@Configuration
//public class JdbcConfig {
//  @Bean
//  public DataSource dataSource() {
//    return new EmbeddedDatabaseBuilder()
//        .setType(EmbeddedDatabaseType.H2)
//        .setName("kakaodb")
//        .addScript("classpath:jdbc/schema.sql")
//        .addScript("classpath:jdbc/data.sql")
//        .build();
//  }
//
//}
