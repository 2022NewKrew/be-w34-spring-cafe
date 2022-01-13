package com.kakao.cafe.Configuration;

import com.kakao.cafe.Repository.ArticleRepository;
import com.kakao.cafe.Repository.JdbcTemplateArticleRepository;
import com.kakao.cafe.Repository.JdbcTemplateUserRepository;
import com.kakao.cafe.Repository.UserRepository;
import com.kakao.cafe.Service.ArticleService;
import com.kakao.cafe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfiguration {

    private DataSource dataSource;

    @Autowired
    public SpringConfiguration(DataSource dataSource){
        this.dataSource = dataSource;
    }
    @Bean
    public UserService userService(){
        return new UserService(userRepository());
    }

    @Bean
    public UserRepository userRepository(){
        return new JdbcTemplateUserRepository(dataSource);
    }

    @Bean
    public ArticleService articleService(){
        return new ArticleService(articleRepository());
    }

    @Bean
    public ArticleRepository articleRepository(){
        return new JdbcTemplateArticleRepository(dataSource);
    }
}
