package com.kakao.cafe.config;

import com.kakao.cafe.controller.ArticleController;
import com.kakao.cafe.controller.UserController;
import com.kakao.cafe.repository.ArticleRepository;
import com.kakao.cafe.repository.ArticleRepositoryImplH2;
import com.kakao.cafe.repository.UserRepository;
import com.kakao.cafe.repository.UserRepositoryImpH2;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserService;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public UserRepository userRepository() {
        return new UserRepositoryImpH2(dataSource);
    }

    @Bean
    public UserService userService() {
        return new UserService(userRepository());
    }

    @Bean
    public UserController userController() {
        return new UserController(userService());
    }

    @Bean
    public ArticleRepository articleRepository() {
        return new ArticleRepositoryImplH2(dataSource, userRepository());
    }

    @Bean
    public ArticleService articleService() {
        return new ArticleService(articleRepository());
    }

    @Bean
    public ArticleController articleController() {
        return new ArticleController(articleService(), userService());
    }
}
