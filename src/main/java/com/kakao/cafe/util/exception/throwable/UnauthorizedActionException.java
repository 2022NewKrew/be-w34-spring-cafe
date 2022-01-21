package com.kakao.cafe.util.exception.throwable;

public class UnauthorizedActionException extends CustomThrowableException {
    public UnauthorizedActionException(String format) {
        super(String.format("당신은 권한이 없습니다! %s", format));
    }
}
