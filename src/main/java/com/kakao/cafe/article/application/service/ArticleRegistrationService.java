package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.adapter.out.persistence.ArticleCreateCommand;
import com.kakao.cafe.article.application.port.in.ArticleRegistrationCommand;
import com.kakao.cafe.article.application.port.in.ArticleRegistrationUseCase;
import com.kakao.cafe.article.application.port.out.ArticleRegistrationPort;

import java.time.LocalDateTime;

public class ArticleRegistrationService implements ArticleRegistrationUseCase {
    private final ArticleRegistrationPort articleRegistrationPort;

    public ArticleRegistrationService(ArticleRegistrationPort articleRegistrationPort) {
        this.articleRegistrationPort = articleRegistrationPort;
    }

    @Override
    public void registerArticle(ArticleRegistrationCommand articleRegistrationCommand, String nickName) {
        articleRegistrationPort.saveArticle(new ArticleCreateCommand(LocalDateTime.now(),
                nickName,
                articleRegistrationCommand.getTitle(),
                articleRegistrationCommand.getContents(),
                0));
    }
}
