package com.kakao.cafe.exception.article;

import com.kakao.cafe.exception.CustomException;
import com.kakao.cafe.exception.ErrorCode;

public class ArticleNotFoundException extends CustomException {

    public ArticleNotFoundException() {
        super(ErrorCode.ARTICLE_NOT_FOUND);
    }
}
