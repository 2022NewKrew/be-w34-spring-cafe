package com.kakao.cafe.article.exception;

public class CannotDeleteException extends RuntimeException {
    public CannotDeleteException() {
        super("삭제할 수 없습니다 (다른 사용자의 댓글이 달려 있습니다.)");
    }
}
