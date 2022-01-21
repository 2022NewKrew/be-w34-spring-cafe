package com.kakao.cafe.util.exception.throwable;

public class UnavailableActionException extends CustomThrowableException {
    public UnavailableActionException(String message) {
        super(String.format("현재 불가능한 행위입니다! : %s", message));
    }
}
