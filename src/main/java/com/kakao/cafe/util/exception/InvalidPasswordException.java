package com.kakao.cafe.util.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(String password) {
        super(String.format("패스워드가 틀립니다! : %s", password));
    }
}
