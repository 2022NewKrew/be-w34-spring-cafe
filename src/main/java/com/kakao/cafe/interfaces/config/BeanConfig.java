package com.kakao.cafe.interfaces.config;

import com.kakao.cafe.application.UserService;
import com.kakao.cafe.application.UserServiceImpl;
import com.kakao.cafe.domain.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    UserService getUserServiceBean(UserRepository userRepository) {
        return new UserServiceImpl(userRepository);
    }

}
