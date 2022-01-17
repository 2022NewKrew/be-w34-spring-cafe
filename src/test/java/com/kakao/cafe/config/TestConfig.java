package com.kakao.cafe.config;

import com.kakao.cafe.user.presentation.mapper.JoinRequestToUserConverter;
import com.kakao.cafe.user.presentation.mapper.UpdateUserInfoRequestToUserInfoConverter;
import com.kakao.cafe.user.presentation.mapper.UserToUserDtoConverter;
import org.modelmapper.Converter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

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
}
