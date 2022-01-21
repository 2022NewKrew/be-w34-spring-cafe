package com.kakao.cafe.domain.comment.exception;

public class UserIdNotMatchException extends IllegalArgumentException {

    public UserIdNotMatchException() {
        super("해당 댓글을 작성한 사용자가 아닙니다.");
    }
}
