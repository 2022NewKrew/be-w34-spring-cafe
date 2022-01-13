package com.kakao.cafe.web.config;

import com.kakao.cafe.web.repository.MemoryUserRepository;
import com.kakao.cafe.web.repository.UserRepository;
import com.kakao.cafe.web.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository() {
        return new MemoryUserRepository();
    }
}
