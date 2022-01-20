package com.kakao.cafe.article.application.port.in;

import com.kakao.cafe.common.exception.ArticleUpdateException;

public interface ArticleUpdateUseCase {
    ArticleUpdateForm findArticleUpdateForm(Long articleId, Long userId) throws ArticleUpdateException;

    void updateArticle(Long articleId, String title, String writerName, String contents);
}
