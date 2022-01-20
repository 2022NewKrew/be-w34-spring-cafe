package com.kakao.cafe.exception.article;

import com.kakao.cafe.exception.CafeException;

public class ArticleNotFoundException extends CafeException {
    public ArticleNotFoundException() {
        super();
    }

    public ArticleNotFoundException(String message) {
        super(message);
    }
}
