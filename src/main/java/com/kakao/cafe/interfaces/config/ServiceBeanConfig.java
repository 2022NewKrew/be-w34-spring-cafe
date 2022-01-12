package com.kakao.cafe.interfaces.config;

import com.kakao.cafe.application.article.FindArticleService;
import com.kakao.cafe.application.article.WriteArticleService;
import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.SignUpUserService;
import com.kakao.cafe.domain.article.FindArticlePort;
import com.kakao.cafe.domain.article.WriteArticlePort;
import com.kakao.cafe.domain.user.FindUserPort;
import com.kakao.cafe.domain.user.SignUpUserPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfig {

    @Bean
    FindUserService getUserServiceBean(FindUserPort findUserPort) {
        return new FindUserService(findUserPort);
    }

    @Bean
    SignUpUserService getSignUpUserServiceBean(FindUserPort findUserPort, SignUpUserPort signUpUserPort) {
        return new SignUpUserService(signUpUserPort, findUserPort);
    }

    @Bean
    FindArticleService getArticleServiceBean(FindArticlePort findArticlePort) {
        return new FindArticleService(findArticlePort);
    }

    @Bean
    WriteArticleService getWriteArticleServiceBean(WriteArticlePort writeArticlePort) {
        return new WriteArticleService(writeArticlePort);
    }
}
