package com.kakao.cafe.interfaces.config;

import com.kakao.cafe.application.ArticleService;
import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.SignUpUserService;
import com.kakao.cafe.domain.article.ArticlePort;
import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.SignUpUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    FindUserService getUserServiceBean(FindUserPort findUserPort) {
        return new FindUserService(findUserPort);
    }

    @Bean
    SignUpUserService getSignUpUserServiceBean(FindUserPort findUserPort, SignUpUserPort signUpUserPort) {
        return new SignUpUserService(signUpUserPort, findUserPort);
    }

    @Bean
    ArticleService getArticleServiceBean(ArticlePort articlePort) {
        return new ArticleService(articlePort);
    }

}
