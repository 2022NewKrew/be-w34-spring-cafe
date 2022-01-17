package com.kakao.cafe.article;

import java.time.LocalDateTime;

public class ArticleDto {

    private final long seq;

    private final String writer;

    private final String title;

    private final String content;

    private final LocalDateTime time;

    public ArticleDto(Article article) {
        this.seq = article.getSeq();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.time = article.getTime();
    }

    public long getSeq() {
        return seq;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return content;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
