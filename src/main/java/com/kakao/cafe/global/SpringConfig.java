package com.kakao.cafe.global;

import com.kakao.cafe.user.HashMapUserRepository;
import com.kakao.cafe.user.SpringJdbcUserRepository;
import com.kakao.cafe.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    @Bean
    public UserRepository userRepository(DataSource dataSource) {
        // return new SpringJdbcUserRepository(dataSource);
        return new HashMapUserRepository();
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:sql/schema.sql")
                .addScript("classpath:sql/test_data.sql").build();
    }
}
