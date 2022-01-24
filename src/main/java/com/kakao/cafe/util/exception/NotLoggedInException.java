package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.NOT_LOGGED_IN_ERROR_MESSAGE;

public class NotLoggedInException extends KakaoCafeException {
    public NotLoggedInException() {
        super(NOT_LOGGED_IN_ERROR_MESSAGE);
    }

    public NotLoggedInException(String message) {
        super(message);
    }
}
