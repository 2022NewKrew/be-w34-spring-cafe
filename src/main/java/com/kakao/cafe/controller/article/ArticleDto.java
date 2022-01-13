package com.kakao.cafe.controller.article;

import com.kakao.cafe.model.Article;

import java.time.LocalDateTime;

public class ArticleDto {
    private final int id;
    private final String title;
    private final String writer;
    private final String contents;
    private final LocalDateTime createDate;

    public ArticleDto(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.writer = article.getWriter();
        this.contents = article.getContents();
        this.createDate = article.getCreateDate();
    }
}
