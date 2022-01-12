package com.kakao.cafe.domain.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Article {
    private static final DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final int articleId;
    private final LocalDateTime time;
    private final String writer;
    private final String title;
    private final String contents;

    public Article(int articleId, String writer, String title, String contents) {
        this.articleId = articleId;
        this.title = title;
        this.writer = writer;
        this.time = LocalDateTime.now();
        this.contents = contents;
    }

    public int getArticleId() { return articleId; }

    public String getTitle() { return title; }

    public String getWriter() { return writer; }

    public String getTime() { return time.format(format); }

    public String getContents() { return contents; }
}
