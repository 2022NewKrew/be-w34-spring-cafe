package com.kakao.cafe.error;

import lombok.Getter;

@Getter
public enum ErrorCode {
    NOT_FOUND("Cannot Find"),
    ALREADY_EXISTS("Already Exists"),
    INVALID_VALUE("Invalid");

    private final String errorMessage;

    ErrorCode(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
