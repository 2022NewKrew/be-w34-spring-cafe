package com.kakao.cafe.exception;

public class AlreadyExistUserException extends RuntimeException {
    public AlreadyExistUserException(String userId) {
        super("Already Exist User (user id: " + userId + ")");
    }
}
