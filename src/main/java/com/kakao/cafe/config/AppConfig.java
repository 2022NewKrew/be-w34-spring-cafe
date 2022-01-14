package com.kakao.cafe.config;

import com.kakao.cafe.domain.article.ArticleRegistrationService;
import com.kakao.cafe.domain.login.LoginService;
import com.kakao.cafe.domain.user.UserSignUpService;
import com.kakao.cafe.domain.user.UserUpdateService;
import com.kakao.cafe.infra.repository.article.ArticleRepository;
import com.kakao.cafe.infra.repository.user.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public LoginService userLoginService(UserRepository userRepository){
        return new LoginService(userRepository);
    }

    @Bean
    public UserSignUpService userSignUpService(UserRepository userRepository){
        return new UserSignUpService(userRepository);
    }

    @Bean
    public UserUpdateService userUpdateService(UserRepository userRepository){
        return new UserUpdateService(userRepository);
    }

    @Bean
    public ArticleRegistrationService articleRegistrationService(ArticleRepository userRepository){
        return new ArticleRegistrationService(userRepository);
    }
}
