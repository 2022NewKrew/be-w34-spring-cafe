package com.kakao.cafe.exception;

public class CustomEmptyDataAccessException extends RuntimeException {

    public CustomEmptyDataAccessException(Throwable cause) {
        super("[ERROR] 존재하지 않는 데이터에 접근했습니다.", cause);
    }
}
