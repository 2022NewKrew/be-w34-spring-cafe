package com.kakao.cafe.common.exception;

public class EntityNotFoundException extends BusinessException{
    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
