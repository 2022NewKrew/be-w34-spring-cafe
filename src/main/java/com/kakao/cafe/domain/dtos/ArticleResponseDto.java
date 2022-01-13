package com.kakao.cafe.domain.dtos;

import com.kakao.cafe.domain.Article;

import java.text.SimpleDateFormat;

public class ArticleResponseDto {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    private final Long id;
    private final String writer;
    private final String title;
    private final String content;
    private final String creationTime;

    public ArticleResponseDto(Article article) {
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.creationTime = dateFormat.format(article.getCreationTime());
    }

    public Long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getCreationTime() {
        return creationTime;
    }
}
