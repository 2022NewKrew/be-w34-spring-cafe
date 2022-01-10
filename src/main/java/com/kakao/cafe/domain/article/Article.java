package com.kakao.cafe.domain.article;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Article {

    private final Title title;
    private final Text text;
    private final Author author;
    private Long articleId;

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }
}
