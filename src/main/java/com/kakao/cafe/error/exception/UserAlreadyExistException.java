package com.kakao.cafe.error.exception;

public class UserAlreadyExistException extends RuntimeException{
    private static final String MESSAGE = "이미 존재하는 유저입니다.";

    public UserAlreadyExistException() {
        super(MESSAGE);
    }
}
