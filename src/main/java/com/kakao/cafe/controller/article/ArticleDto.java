package com.kakao.cafe.controller.article;

import com.kakao.cafe.model.article.Article;

import java.time.LocalDateTime;

public class ArticleDto {
    private final int id;
    private final String title;
    private final String writer;
    private final String contents;
    private final LocalDateTime createDate;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle().getValue();
        this.writer = article.getWriter().getValue();
        this.contents = article.getContents().getValue();
        this.createDate = article.getCreateDate();
    }
}
