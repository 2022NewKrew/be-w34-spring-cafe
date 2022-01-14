package com.kakao.cafe.interfaces.article.validation;

public enum ArticleErrorCode {
    NON_EXISTS_ARTICLE_INDEX("존재하지 않는 글 번호입니다.")
    ;

    private final String message;

    ArticleErrorCode(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
