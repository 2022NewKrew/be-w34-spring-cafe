package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.USER_NOT_FOUND_ERROR_MESSAGE;

public class UserNotFoundException extends KakaoCafeException {
    public UserNotFoundException() {
        super(USER_NOT_FOUND_ERROR_MESSAGE);
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
