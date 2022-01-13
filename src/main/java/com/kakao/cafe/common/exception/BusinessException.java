package com.kakao.cafe.common.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException{

    private final ErrorType errorType;

    public BusinessException(ErrorType errorType){
        this.errorType = errorType;
    }
}
