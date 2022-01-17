package com.kakao.cafe.config;

import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.JdbcUserRepository;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.ArticleServiceImpl;
import com.kakao.cafe.service.UserService;
import com.kakao.cafe.service.UserServiceImpl;
import com.kakao.cafe.util.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AppConfig {

    @Bean
    public ArticleService articleService() { return new ArticleServiceImpl(articleRepository());}

    @Bean
    public ArticleRepository articleRepository() {
        return new ArticleRepository();
    }

    @Bean
        public UserService userService() {
        return new UserServiceImpl(jdbcUserRepository());
    }

//    @Bean
//    public UserService userService() {
//        return new UserServiceImpl(userRepository());
//    }
//
//    @Bean
//    public UserRepository userRepository() {
//        return new UserRepository(new SecurityConfig().passwordEncoder());
//    }


    @Bean
    public JdbcUserRepository jdbcUserRepository() {
        return new JdbcUserRepository(jdbcTemplate(), userMapper(), new SecurityConfig().passwordEncoder());
    }

    @Bean
    public JdbcTemplate jdbcTemplate() {
        return new JdbcTemplate(new JdbcConfig().dataSource());
    }

    @Bean
    public UserMapper userMapper() {
        return new UserMapper();
    }

}
