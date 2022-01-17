package com.kakao.cafe.common.exception;

import lombok.Getter;

@Getter
public class AuthenticationException extends RuntimeException {

    private final String message;

    public AuthenticationException(String message) {
        this.message = message;
    }

    public static <T> void throwAuthFailure(String reason, T value) throws AuthenticationException {
        throw new AuthenticationException("인증에 실패하였습니다: " + reason + ". value: " + value);
    }
}
