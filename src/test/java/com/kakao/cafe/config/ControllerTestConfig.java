package com.kakao.cafe.config;

import com.kakao.cafe.post.presentation.mapper.CommentRequestToCommentConverter;
import com.kakao.cafe.post.presentation.mapper.PostRequestToPostConverter;
import com.kakao.cafe.post.presentation.mapper.PostToPostDetailConverter;
import com.kakao.cafe.post.presentation.mapper.PostToPostDtoConverter;
import com.kakao.cafe.user.presentation.mapper.JoinRequestToUserConverter;
import com.kakao.cafe.user.presentation.mapper.UpdateUserInfoRequestToUserInfoConverter;
import com.kakao.cafe.user.presentation.mapper.UserToUserDtoConverter;
import org.modelmapper.Converter;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.util.List;

@TestConfiguration
public class ControllerTestConfig {
    @Bean
    List<Converter<?,?>> converters(){
        return List.of(
                new UserToUserDtoConverter(),
                new JoinRequestToUserConverter(),
                new UpdateUserInfoRequestToUserInfoConverter(),
                new PostRequestToPostConverter(),
                new PostToPostDetailConverter(),
                new PostToPostDtoConverter(),
                new CommentRequestToCommentConverter()
        );
    }
}
