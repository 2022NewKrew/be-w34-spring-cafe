package com.kakao.cafe.config;

import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ArticleServiceImpl;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ArticleService articleService() { return new ArticleServiceImpl(articleRepository());}

    @Bean
    public ArticleRepository articleRepository() {
        return new ArticleRepository();
    }

    @Bean
    public UserService userService() { return new UserServiceImpl(userRepository());}

    @Bean
    public UserRepository userRepository() { return new UserRepository();}
}
