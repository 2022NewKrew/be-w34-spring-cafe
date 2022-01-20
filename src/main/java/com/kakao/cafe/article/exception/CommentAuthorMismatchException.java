package com.kakao.cafe.article.exception;

import com.kakao.cafe.common.exception.BusinessException;
import com.kakao.cafe.common.exception.ErrorType;

public class CommentAuthorMismatchException extends BusinessException {

    public CommentAuthorMismatchException() {
        super(ErrorType.COMMENT_AUTHOR_MISMATCH);
    }
}
