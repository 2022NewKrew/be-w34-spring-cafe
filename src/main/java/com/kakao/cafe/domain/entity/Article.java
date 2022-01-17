package com.kakao.cafe.domain.entity;

import com.kakao.cafe.web.util.TimeStringParser;

import java.time.LocalDateTime;

public class Article {
    private final long articleId;
    private final LocalDateTime time;
    private final String writer;
    private final String title;
    private final String content;

    public Article(long articleId, String writer, String title, String contents, LocalDateTime time) {
        this.articleId = articleId;
        this.writer = writer;
        this.title = title;
        this.content = contents;
        this.time = time;
    }

    public long getArticleId() { return articleId; }

    public String getTitle() { return title; }

    public String getWriter() { return writer; }

    public String getTime() { return TimeStringParser.parseTimeToString(time); }

    public String getContent() { return content; }
}
