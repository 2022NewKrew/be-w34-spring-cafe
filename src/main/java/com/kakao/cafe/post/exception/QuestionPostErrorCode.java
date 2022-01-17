package com.kakao.cafe.post.exception;

public enum QuestionPostErrorCode {
    ID_NOT_FOUND(404, "일치하는 아이디가 존재하지 않습니다.");

    private final int code;
    private final String description;

    QuestionPostErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}
