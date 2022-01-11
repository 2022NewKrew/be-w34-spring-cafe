package com.kakao.cafe.article.exception;

import com.kakao.cafe.common.exception.NotFoundException;

public class ArticleNotFoundException extends NotFoundException {

    public ArticleNotFoundException() {
        super("해당 게시글이 존재하지 않습니다.");
    }
}
