package com.kakao.cafe.exception;

import org.springframework.validation.Errors;

public class InvalidDtoException extends CustomException {

    private final Errors errors;

    public InvalidDtoException(Errors errors) {
        super(ErrorCode.INVALID_DTO);
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return errors.getFieldErrors().get(0).getDefaultMessage();
    }
}
