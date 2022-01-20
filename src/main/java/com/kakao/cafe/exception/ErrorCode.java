package com.kakao.cafe.exception;

public enum ErrorCode {
    USERID_DUPLICATION(400, "중복된 ID 입니다."),
    INVALID_INPUT(400, "입력값이 유효하지 않습니다."),
    LOGIN_USER_NOT_FOUND(400, "사용자 아이디를 잘못 입력했습니다."),
    LOGIN_WRONG_PASSWORD(400, "비밀번호를 잘못 입력했습니다."),
    FORBIDDEN_MODIFY_POST(403, "게시글 수정/삭제는 작성자만 가능합니다."),
    USER_NOT_FOUND(404, "회원을 찾을 수 없습니다."),
    POST_NOT_FOUND(404, "게시글을 찾을 수 없습니다."),
    NO_USER_MATCHED_WRITER(404, "일치하는 작성자 정보가 없습니다."),
    INTER_SERVER_ERROR(500, "INTER SERVER ERROR"),
    ;

    private final int status;
    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
