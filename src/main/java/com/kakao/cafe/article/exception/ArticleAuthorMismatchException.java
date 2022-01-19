package com.kakao.cafe.article.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;

public class ArticleAuthorMismatchException extends BusinessException {

    public ArticleAuthorMismatchException() {
        super(ErrorType.ARTICLE_AUTHOR_MISMATCH);
    }
}
