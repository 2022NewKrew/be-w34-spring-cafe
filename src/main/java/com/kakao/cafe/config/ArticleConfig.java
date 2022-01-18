package com.kakao.cafe.config;

import com.kakao.cafe.article.application.port.in.ArticleCommonQueryUserCase;
import com.kakao.cafe.article.application.port.in.ArticleRegistrationUseCase;
import com.kakao.cafe.article.application.port.in.ArticleUpdateUseCase;
import com.kakao.cafe.article.application.port.out.ArticleCommonQueryPort;
import com.kakao.cafe.article.application.port.out.ArticleRegistrationPort;
import com.kakao.cafe.article.application.port.out.LoadUpdateInfoPort;
import com.kakao.cafe.article.application.service.ArticleCommonQueryService;
import com.kakao.cafe.article.application.service.ArticleRegistrationService;
import com.kakao.cafe.article.application.service.ArticleUpdateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {
    @Bean
    public ArticleRegistrationUseCase articleRegistrationService(ArticleRegistrationPort articleRegistrationPort) {
        return new ArticleRegistrationService(articleRegistrationPort);
    }

    @Bean
    public ArticleCommonQueryUserCase articleCommonQueryUserCase(ArticleCommonQueryPort articleCommonQueryPort) {
        return new ArticleCommonQueryService(articleCommonQueryPort);
    }

    @Bean
    public ArticleUpdateUseCase articleUpdateUseCase(LoadUpdateInfoPort loadUpdateInfoPort){
        return new ArticleUpdateService(loadUpdateInfoPort);
    }
}
