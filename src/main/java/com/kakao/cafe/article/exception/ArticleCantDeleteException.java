package com.kakao.cafe.article.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;

public class ArticleCantDeleteException extends BusinessException {

    public ArticleCantDeleteException() {
        super(ErrorType.ARTICLE_CANT_DELETE);
    }
}
