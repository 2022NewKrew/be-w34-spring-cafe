package com.kakao.cafe.config;

import com.kakao.cafe.user.domain.repository.UserRepository;
import com.kakao.cafe.user.persistence.JdbcUserRepository;
import com.kakao.cafe.user.persistence.mapper.UserRowMapper;
import com.kakao.cafe.user.presentation.mapper.JoinRequestToUserConverter;
import com.kakao.cafe.user.presentation.mapper.UpdateUserInfoRequestToUserInfoConverter;
import com.kakao.cafe.user.presentation.mapper.UserToUserDtoConverter;
import com.kakao.cafe.util.MyJdbcTemplate;
import org.modelmapper.Converter;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;
import java.util.List;

@TestConfiguration
public class TestConfig {
    @Bean
    List<Converter<?,?>> converters(){
        return List.of(
                new UserToUserDtoConverter(),
                new JoinRequestToUserConverter(),
                new UpdateUserInfoRequestToUserInfoConverter()
        );
    }

    @Bean
    UserRepository userRepository(DataSource dataSource){
        MyJdbcTemplate myJdbcTemplate = new MyJdbcTemplate(dataSource);
        UserRowMapper userRowMapper = new UserRowMapper();
        return new JdbcUserRepository(myJdbcTemplate, userRowMapper);
    }
}
