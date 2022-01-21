package com.kakao.cafe.domain.article.exception;

public class ArticleNotDeletableException extends IllegalArgumentException {

    public ArticleNotDeletableException() {
        super("다른 사람이 작성한 댓글이 있어 글을 삭제할 수 없습니다.");
    }
}
