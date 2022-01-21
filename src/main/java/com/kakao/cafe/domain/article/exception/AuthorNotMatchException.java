package com.kakao.cafe.domain.article.exception;

public class AuthorNotMatchException extends IllegalArgumentException {

    public AuthorNotMatchException() {
        super("해당 게시글을 작성한 사용자가 아닙니다.");
    }
}
