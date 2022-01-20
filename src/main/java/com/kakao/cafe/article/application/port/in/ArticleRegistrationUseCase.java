package com.kakao.cafe.article.application.port.in;

public interface ArticleRegistrationUseCase {
    void registerArticle(ArticleRegistrationCommand articleRegistrationCommand, String nickName, Long userKey);
}
