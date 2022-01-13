package com.kakao.cafe.exception;

public enum ErrorCode {
    USERID_DUPLICATION(400, "USER-ERR-400", "중복된 ID 입니다."),
    NO_USER_MATCHED_INPUT(400, "USER-ERR-400", "입력 정보와 일치하는 회원 정보가 없습니다."),
    INVALID_INPUT(400, "USER-ERR-400", "입력값이 유효하지 않습니다."),
    USER_NOT_FOUND(404, "COMMON-ERR-404", "회원을 찾을 수 없습니다."),
    POST_NOT_FOUND(404, "COMMON-ERR-404", "게시글을 찾을 수 없습니다."),
    INTER_SERVER_ERROR(500, "COMMON-ERR-500", "INTER SERVER ERROR"),
    ;

    private final int status;
    private final String errorCode;
    private final String message;

    ErrorCode(int status, String errorCode, String message) {
        this.status = status;
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }
}
