package com.kakao.cafe.config;

import com.kakao.cafe.repository.PostRepository;
import com.kakao.cafe.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConfig {

    @Bean
    public UserRepository userRepository() {
        return new UserRepository();
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }
}
