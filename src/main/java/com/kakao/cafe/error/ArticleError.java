package com.kakao.cafe.error;

public enum ArticleError {
    NOT_FOUND("입력한 게시글을 찾을 수 없습니다.");

    private final String message;

    ArticleError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
