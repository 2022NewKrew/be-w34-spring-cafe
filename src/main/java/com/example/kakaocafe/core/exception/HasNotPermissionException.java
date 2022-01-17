package com.example.kakaocafe.core.exception;

public class HasNotPermissionException extends BusinessException {
    public HasNotPermissionException() {
        super("권한이 없는 요청입니다");
    }
}
