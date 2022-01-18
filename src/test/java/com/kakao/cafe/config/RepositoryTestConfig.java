package com.kakao.cafe.config;

import com.kakao.cafe.post.domain.repository.PostRepository;
import com.kakao.cafe.post.persistence.JdbcPostRepository;
import com.kakao.cafe.post.persistence.mapper.PostRowMapper;
import com.kakao.cafe.user.domain.repository.UserRepository;
import com.kakao.cafe.user.persistence.JdbcUserRepository;
import com.kakao.cafe.user.persistence.mapper.UserRowMapper;
import com.kakao.cafe.util.MyJdbcTemplate;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

//@TestConfiguration
public class RepositoryTestConfig {
    @Bean
    UserRepository userRepository(MyJdbcTemplate myJdbcTemplate){
        UserRowMapper userRowMapper = new UserRowMapper();
        return new JdbcUserRepository(myJdbcTemplate, userRowMapper);
    }

    @Bean
    PostRepository postRepository(MyJdbcTemplate myJdbcTemplate){
        PostRowMapper postRowMapper = new PostRowMapper();
        return new JdbcPostRepository(myJdbcTemplate, postRowMapper);
    }

    @Bean
    MyJdbcTemplate myJdbcTemplate(DataSource dataSource){
        return new MyJdbcTemplate(dataSource);
    }

    @Bean
    PlatformTransactionManager platformTransactionManager(DataSource dataSource){
        return new DataSourceTransactionManager(dataSource);
    }
}
