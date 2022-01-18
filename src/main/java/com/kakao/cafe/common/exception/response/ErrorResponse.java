package com.kakao.cafe.common.exception.response;

import com.kakao.cafe.common.exception.data.ErrorCode;
import lombok.Getter;

@Getter
public class ErrorResponse {
    private final Integer code;
    private final String message;
    private final String detail;

    public ErrorResponse(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
        this.detail = errorCode.getDetail();
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode);
    }
}
