package com.kakao.cafe.util.exception;

public class UnauthorizedAction extends CustomException {
    public UnauthorizedAction(Exception exception, String format) {
        super(exception, String.format("당신은 권한이 없습니다! %s", format));
    }
}
