package com.kakao.cafe.domain.article.exception;

public class ArticleNotFoundException extends IllegalArgumentException {

    public ArticleNotFoundException(Long id) {
        super(id + " 에 해당하는 글을 찾을 수 없습니다");
    }

    public ArticleNotFoundException(String userId) {
        super(userId + " 에 해당하는 사용자가 작성한 글을 찾을 수 없습니다.");
    }
}
