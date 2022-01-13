package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;

public class ArticleViewDTO {

    private final Long id;
    private final String writer;
    private final String title;
    private final String writingTime;
    private final Long countOfComment;

    public ArticleViewDTO(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.writingTime = article.getWritingTime();
        this.countOfComment = article.getCountOfComment();
    }
}
