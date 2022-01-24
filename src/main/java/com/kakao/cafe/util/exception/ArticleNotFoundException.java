package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.ARTICLE_NOT_FOUND_ERROR_MESSAGE;

public class ArticleNotFoundException extends KakaoCafeException {
    public ArticleNotFoundException() {
        super(ARTICLE_NOT_FOUND_ERROR_MESSAGE);
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
