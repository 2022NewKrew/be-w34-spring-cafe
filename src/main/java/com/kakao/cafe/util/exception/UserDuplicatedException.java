package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.USER_DUPLICATED_ERROR_MESSAGE;

public class UserDuplicatedException extends KakaoCafeException {
    public UserDuplicatedException() {
        super(USER_DUPLICATED_ERROR_MESSAGE);
    }

    public UserDuplicatedException(String message) {
        super(message);
    }
}
