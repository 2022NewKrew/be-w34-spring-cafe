package com.kakao.cafe.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiError {
    private final String message;
    private final int status;

    ApiError(Throwable throwable, HttpStatus status) {
        this(throwable.getMessage(), status);
    }

    ApiError(String message, HttpStatus status) {
        this.message = message;
        this.status = status.value();
    }

}
