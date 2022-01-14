package com.kakao.cafe.interfaces.article.validation;

public class NonExistsArticleIndexException extends IllegalArgumentException {
    public NonExistsArticleIndexException() {
        super(ArticleErrorCode.NON_EXISTS_ARTICLE_INDEX.getMessage());
    }
}
