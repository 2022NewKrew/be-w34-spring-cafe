package com.kakao.cafe.util.exception;

import static com.kakao.cafe.util.Constants.NO_ADMIN_ERROR_MESSAGE;

public class NoAdminException extends KakaoCafeException {
    public NoAdminException() {
        super(NO_ADMIN_ERROR_MESSAGE);
    }

    public NoAdminException(String message) {
        super(message);
    }
}
