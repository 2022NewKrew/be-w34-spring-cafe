package com.kakao.cafe.exception;

public class AlreadyExistUserException extends RuntimeException {
    private final String message;

    public AlreadyExistUserException(String userId) {
        this.message = "Already Exist User (user id: " + userId + ")";
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
