package com.kakao.cafe.common.exception;

public class ArticleUpdateException extends BusinessException {
    public ArticleUpdateException() {
        super("해당 아이디로 작성된 글이 아닙니다.");
    }
}
