package com.kakao.cafe.config;

import com.kakao.cafe.article.application.port.in.*;
import com.kakao.cafe.article.application.port.out.*;
import com.kakao.cafe.article.application.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArticleConfig {
    @Bean
    public ArticleRegistrationUseCase articleRegistrationService(ArticleRegistrationPort articleRegistrationPort) {
        return new ArticleRegistrationService(articleRegistrationPort);
    }

    @Bean
    public ArticleCommonQueryUserCase articleCommonQueryUserCase(ArticleCommonQueryPort articleCommonQueryPort,
                                                                 CommentCommonQueryPort commentCommonQueryPort) {
        return new ArticleCommonQueryService(articleCommonQueryPort, commentCommonQueryPort);
    }

    @Bean
    public ArticleUpdateUseCase articleUpdateUseCase(LoadUpdateInfoPort loadUpdateInfoPort) {
        return new ArticleUpdateService(loadUpdateInfoPort);
    }

    @Bean
    public ArticleDeletionUseCase articleDeletionUseCase(DeleteArticlePort deleteArticlePort) {
        return new ArticleDeletionService(deleteArticlePort);
    }

    @Bean
    public CommentRegistrationUseCase commentRegistrationUseCase(RegisterCommentPort registerCommentPort) {
        return new CommentRegistrationService(registerCommentPort);
    }

    @Bean
    public CommentDeletionUseCase commentDeletionUseCase(DeleteCommentPort deleteCommentPort) {
        return new CommentDeletionService(deleteCommentPort);
    }
}
