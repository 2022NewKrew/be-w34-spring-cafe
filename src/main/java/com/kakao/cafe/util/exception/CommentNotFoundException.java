package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.COMMENT_NOT_FOUND_ERROR_MESSAGE;

public class CommentNotFoundException extends KakaoCafeException {
    public CommentNotFoundException() {
        super(COMMENT_NOT_FOUND_ERROR_MESSAGE);
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
