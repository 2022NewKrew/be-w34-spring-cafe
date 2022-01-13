package com.kakao.cafe.exception;

import org.springframework.http.HttpStatus;

public class CustomEmptyDataAccessException extends RuntimeException {

    public static final HttpStatus CODE = HttpStatus.NOT_FOUND;

    public CustomEmptyDataAccessException(Throwable cause) {
        super("[ERROR] 존재하지 않는 데이터에 접근했습니다.", cause);
    }
}
