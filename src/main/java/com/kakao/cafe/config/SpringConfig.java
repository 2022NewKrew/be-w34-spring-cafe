package com.kakao.cafe.config;

import com.kakao.cafe.domain.Article;
import com.kakao.cafe.domain.ArticleDTO;
import com.kakao.cafe.domain.UserAccount;
import com.kakao.cafe.domain.UserAccountDTO;
import com.kakao.cafe.repository.Repository;
import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.article.ArticleRepositoryQueryAndNameSpace;
import com.kakao.cafe.repository.user.UserAccountRepository;
import com.kakao.cafe.repository.user.UserAccountRepositoryQueryAndNameSpace;
import com.kakao.cafe.service.ArticleService;
import com.kakao.cafe.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public UserAccountService UserAccountService(){
        return new UserAccountService(UserRepository(), getPasswordEncoder());
    }

    @Bean
    public ArticleService ArticleService(){
        return new ArticleService(ArticleRepository());
    }

    @Bean
    public UserAccountRepositoryQueryAndNameSpace getUserAccountRepositoryQueryAndNameSpace(){
        return new UserAccountRepositoryQueryAndNameSpace();
    }

    @Bean
    public Repository<UserAccount, UserAccountDTO, String> UserRepository(){
//        return new UserAccountNoDbUseRepository();
        return new UserAccountRepository(dataSource, getUserAccountRepositoryQueryAndNameSpace());
    }

    @Bean
    public ArticleRepositoryQueryAndNameSpace getArticleRepositoryQueryAndNameSpace(){
        return new ArticleRepositoryQueryAndNameSpace();
    }

    @Bean
    public Repository<Article, ArticleDTO, Integer> ArticleRepository(){
        //return new ArticleNoBdUseRepository();
        return new ArticleRepository(dataSource, getArticleRepositoryQueryAndNameSpace());
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
