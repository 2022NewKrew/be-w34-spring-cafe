package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.NOT_MY_ARTICLE_ERROR_MESSAGE;

public class NotMyArticleException extends KakaoCafeException {
    public NotMyArticleException() {
        super(NOT_MY_ARTICLE_ERROR_MESSAGE);
    }

    public NotMyArticleException(String message) {
        super(message);
    }
}
