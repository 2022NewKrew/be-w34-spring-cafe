package com.kakao.cafe.user.exception;

public class NotFoundUserIdException extends UserException {
    private final String notFoundUserId;

    public NotFoundUserIdException(String notFoundUserId) {
        this.notFoundUserId = notFoundUserId;
    }

    public String getNotFoundUserId() {
        return notFoundUserId;
    }
}
