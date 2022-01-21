package com.kakao.cafe.config;

import com.kakao.cafe.thread.repository.CommentRepository;
import com.kakao.cafe.thread.repository.PostRepository;
import com.kakao.cafe.thread.repository.SpringJdbcCommentRepository;
import com.kakao.cafe.thread.repository.SpringJdbcPostRepository;
import com.kakao.cafe.user.repository.SpringJdbcUserRepository;
import com.kakao.cafe.user.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
public class RepositoryConfig {
    @Bean
    public UserRepository userRepository(DataSource dataSource) {
        return new SpringJdbcUserRepository(dataSource);
    }

    @Bean
    public PostRepository postRepository(DataSource dataSource) {
        return new SpringJdbcPostRepository(dataSource);
    }

    @Bean
    public CommentRepository commentRepository(DataSource dataSource) {
        return new SpringJdbcCommentRepository(dataSource);
    }

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2)
                                            .addScript("classpath:sql/schema.sql")
                                            .addScript("classpath:sql/test_data.sql")
                                            .build();
    }
}
