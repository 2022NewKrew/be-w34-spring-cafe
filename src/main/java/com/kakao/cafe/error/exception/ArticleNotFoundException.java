package com.kakao.cafe.error.exception;

import static com.kakao.cafe.Constant.MESSAGE_ARTICLE_NOT_FOUND;

public class ArticleNotFoundException extends RuntimeException{
    public ArticleNotFoundException() {
        super(MESSAGE_ARTICLE_NOT_FOUND);
    }
}
