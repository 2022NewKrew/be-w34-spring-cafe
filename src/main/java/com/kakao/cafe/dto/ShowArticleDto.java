package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;

public class ShowArticleDto {
    private final long id;
    private final String writer;
    private final String title;
    private final String contents;

    public ShowArticleDto(long id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public ShowArticleDto(Article article) {
        id = article.getId();
        writer = article.getWriter();
        title = article.getTitle();
        contents = article.getContents();
    }

    public long getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
