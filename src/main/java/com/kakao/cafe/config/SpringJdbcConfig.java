package com.kakao.cafe.config;

import com.kakao.cafe.domain.post.PostMapper;
import com.kakao.cafe.domain.post.PostRepository;
import com.kakao.cafe.domain.post.impl.JdbcTemplatePostRepository;
import com.kakao.cafe.domain.user.UserMapper;
import com.kakao.cafe.domain.user.UserRepository;
import com.kakao.cafe.domain.user.impl.JdbcTemplateUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class SpringJdbcConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .setName("kakaodb")
                .addScript("classpath:db/schema.sql")
                .addScript("classpath:db/data.sql")
                .build();
    }

    @Bean
    public PostRepository postRepository(DataSource dataSource, PostMapper postMapper) {
        return new JdbcTemplatePostRepository(dataSource, postMapper);
    }

    @Bean
    public UserRepository userRepository(DataSource dataSource, UserMapper userMapper) {
        return new JdbcTemplateUserRepository(dataSource, userMapper);
    }
}
