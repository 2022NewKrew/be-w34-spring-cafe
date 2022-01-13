package com.kakao.cafe.domain.user.exceptions;

public class UserIdDuplicationException extends Exception {

    public UserIdDuplicationException(String message) {
        super(message);
    }
}
