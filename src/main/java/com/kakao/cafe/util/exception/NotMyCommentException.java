package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.NOT_MY_COMMENT_ERROR_MESSAGE;

public class NotMyCommentException extends KakaoCafeException {
    public NotMyCommentException() {
        super(NOT_MY_COMMENT_ERROR_MESSAGE);
    }

    public NotMyCommentException(String message) {
        super(message);
    }
}
