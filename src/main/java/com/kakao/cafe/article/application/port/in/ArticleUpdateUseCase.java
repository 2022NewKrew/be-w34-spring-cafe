package com.kakao.cafe.article.application.port.in;

import com.kakao.cafe.common.exception.ArticleUpdateException;

public interface ArticleUpdateUseCase {
    ArticleUpdateCommand updateArticle(Long articleId, Long userId) throws ArticleUpdateException;

    void putUpdatedArticle(Long articleId, String title, String writerName, String contents);
}
