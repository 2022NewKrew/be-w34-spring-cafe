package com.kakao.cafe.common.exception.dto;

import org.springframework.validation.FieldError;

public class CustomFieldError {

    private final String field;
    private final Object value;
    private final String reason;

    public CustomFieldError(String field, Object value, String reason) {
        this.field = field;
        this.value = value;
        this.reason = reason;
    }

    public static CustomFieldError of(FieldError fieldError) {
        return new CustomFieldError(
            fieldError.getField(),
            fieldError.getRejectedValue(),
            fieldError.getDefaultMessage()
        );
    }
}
