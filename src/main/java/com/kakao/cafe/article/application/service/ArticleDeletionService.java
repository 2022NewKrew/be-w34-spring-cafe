package com.kakao.cafe.article.application.service;

import com.kakao.cafe.article.application.port.in.ArticleDeletionUseCase;
import com.kakao.cafe.article.application.port.out.DeleteArticlePort;
import com.kakao.cafe.common.exception.DeletionException;

public class ArticleDeletionService implements ArticleDeletionUseCase {
    private final DeleteArticlePort deleteArticlePort;

    public ArticleDeletionService(DeleteArticlePort deleteArticlePort) {
        this.deleteArticlePort = deleteArticlePort;
    }

    @Override
    public void deleteArticle(String articleId, Long userId) {
        Boolean isAbleToDelete = deleteArticlePort.isAbleToDelete(articleId, userId);
        if (!isAbleToDelete) throw new DeletionException();
        deleteArticlePort.delete(articleId);
    }
}
