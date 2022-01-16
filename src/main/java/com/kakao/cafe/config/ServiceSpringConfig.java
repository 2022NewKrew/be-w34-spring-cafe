package com.kakao.cafe.config;

import com.kakao.cafe.repository.article.ArticleRepository;
import com.kakao.cafe.repository.user.UserRepository;
import com.kakao.cafe.service.article.ArticleCreateService;
import com.kakao.cafe.service.article.ArticleFindService;
import com.kakao.cafe.service.user.UserCreateService;
import com.kakao.cafe.service.user.UserFindService;
import com.kakao.cafe.service.user.UserUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceSpringConfig {

    @Bean
    public UserCreateService getUserCreateService(UserRepository userRepository) {
        return new UserCreateService(userRepository);
    }

    @Bean
    public UserFindService getUserFindService(UserRepository userRepository) {
        return new UserFindService(userRepository);
    }

    @Bean
    public UserUpdateService getUserUpdateService(UserRepository userRepository) {
        return new UserUpdateService(userRepository);
    }

    @Bean
    public ArticleCreateService getArticleCreateService(ArticleRepository articleRepository) {
        return new ArticleCreateService(articleRepository);
    }

    @Bean
    public ArticleFindService getArticleFindService(ArticleRepository articleRepository) {
        return new ArticleFindService(articleRepository);
    }
}
