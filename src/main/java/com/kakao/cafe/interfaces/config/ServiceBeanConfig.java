package com.kakao.cafe.interfaces.config;

import com.kakao.cafe.application.article.FindArticleService;
import com.kakao.cafe.application.article.WriteArticleService;
import com.kakao.cafe.application.user.FindUserService;
import com.kakao.cafe.application.user.SignUpUserService;
import com.kakao.cafe.application.user.UpdateUserService;
import com.kakao.cafe.domain.article.ArticleDaoPort;
import com.kakao.cafe.domain.user.UserDaoPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeanConfig {

    @Bean
    FindUserService getFindUserServiceBean(UserDaoPort userDaoPort) {
        return new FindUserService(userDaoPort);
    }

    @Bean
    SignUpUserService getSignUpUserServiceBean(UserDaoPort userDaoPort) {
        return new SignUpUserService(userDaoPort);
    }

    @Bean
    UpdateUserService getUpdateUserServiceBean(UserDaoPort userDaoPort) {
        return new UpdateUserService(userDaoPort);
    }

    @Bean
    FindArticleService getArticleServiceBean(ArticleDaoPort articleDaoPort) {
        return new FindArticleService(articleDaoPort);
    }

    @Bean
    WriteArticleService getWriteArticleServiceBean(ArticleDaoPort articleDaoPort) {
        return new WriteArticleService(articleDaoPort);
    }

}
