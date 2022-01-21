package com.kakao.cafe.common.exception.data;

public enum ErrorCode {

    USER_NOT_FOUND(1000, "유저 조회 오류", "해당 유저가 존재하지 않습니다."),
    PASSWORD_INCORRECT(1001, "로그인 오류", "패스워드가 올바르지 않습니다."),
    IDENTIFICATION_NOT_FOUND(1002, "로그인 오류", "로그인이 필요합니다."),
    UPDATE_USER_ID_INCORRECT(2000, "개인 정보 수정 실패", "자신의 정보만 수정 가능합니다."),
    UPDATE_PASSWORD_INCORRECT(2001, "개인 정보 수정 실패", "패스워드가 올바르지 않습니다."),
    ARTICLE_UPDATER_INCORRECT(3000, "게시글 수정 실패", "다른 사람의 글을 수정할 수 없다."),
    ARTICLE_NOT_FOUND(3001, "게시글 없음", "게시글을 찾을 수 없습니다."),
    REPLY_NOT_FOUND(3002, "댓글 없음", "댓글을 찾을 수 없습니다."),
    ARTICLE_DELETER_INCORRECT(4000, "게시글 삭제 권한 없음", "다른 사람의 글을 삭제할 수 없다."),
    REPLY_DELETER_INCORRECT(4001, "댓글 삭제 권한 없음", "다른 사람의 댓글은 삭제할 수 없습니다."),
    DATABASE_ERROR(5000, "데이터베이스 오류", "데이터베이스 오류");

    private Integer code;
    private String message;
    private String detail;

    ErrorCode(Integer code, String message, String detail) {
        this.code = code;
        this.message = message;
        this.detail = detail;
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDetail() {
        return detail;
    }
}
