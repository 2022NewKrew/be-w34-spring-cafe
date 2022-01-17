package com.kakao.cafe.application.article.validation;

public class NonExistsArticleIdException extends IllegalArgumentException {
    public NonExistsArticleIdException() {
        super(ArticleErrorCode.NON_EXISTS_ARTICLE_INDEX.getMessage());
    }
}
