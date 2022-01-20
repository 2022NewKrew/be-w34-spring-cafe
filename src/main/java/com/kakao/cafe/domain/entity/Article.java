package com.kakao.cafe.domain.entity;

import com.kakao.cafe.util.TimeStringParser;

import java.time.LocalDateTime;

public class Article {
    private final long articleId;
    private final LocalDateTime time;
    private final String writerId;
    private final String title;
    private final String content;

    public Article(long articleId, String writerId, String title, String contents, LocalDateTime time) {
        this.articleId = articleId;
        this.writerId = writerId;
        this.title = title;
        this.content = contents;
        this.time = time;
    }

    public long getArticleId() { return articleId; }

    public String getTitle() { return title; }

    public String getWriterId() { return writerId; }

    public String getTime() { return TimeStringParser.parseTimeToString(time); }

    public String getContent() { return content; }
}
