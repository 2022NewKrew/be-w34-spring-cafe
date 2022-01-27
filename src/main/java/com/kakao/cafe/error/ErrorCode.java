package com.kakao.cafe.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    NOT_FOUND(404, "COMMON-ERR-404", "PAGE NOT FOUND"),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR"),
    USER_ID_DUPLICATION(400, "USER-DUP-ERR-400", "USER ID DUPLICATED"),
    USER_NOT_REGISTERED(400, "USER-REG-ERR-400", "NOT REGISTERED USER ID"),
    INVALID_PASSWORD(400, "INVALID-PASSWORD-400", "INVALID PASSWORD"),
    UNAUTHORIZED(400, "UNAUTHORIZED-400", "UNAUTHORIZED"),
    ;

    private final int status;
    private final String errorCode;
    private final String message;
}
