package com.kakao.cafe.common.exception.dto;

import com.kakao.cafe.common.exception.ErrorCode;
import java.time.LocalDateTime;
import org.springframework.validation.BindingResult;

public class ErrorResponse {

    private final LocalDateTime timeStamp = LocalDateTime.now();
    private final String code;
    private final String message;

    private final CustomFieldErrors customFieldErrors;

    private ErrorResponse(ErrorCode errorCode, CustomFieldErrors customFieldErrors) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.customFieldErrors = customFieldErrors;
    }

    private ErrorResponse(ErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.customFieldErrors = CustomFieldErrors.of();
    }

    public static ErrorResponse of(ErrorCode errorCode, BindingResult bindingResult){
        return new ErrorResponse(errorCode, CustomFieldErrors.of(bindingResult.getFieldErrors()));
    }

    public static ErrorResponse of(ErrorCode errorCode){
        return new ErrorResponse(errorCode);
    }
}
