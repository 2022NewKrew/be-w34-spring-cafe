package com.kakao.cafe.article.application.port.in;

public interface ArticleDeletionUseCase {

    void deleteArticle(String articleId, Long userId);
}
