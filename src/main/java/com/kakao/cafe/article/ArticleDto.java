package com.kakao.cafe.article;

import java.time.LocalDateTime;

public class ArticleDto {

    private final long seq;

    private final long userSeq;

    private final String writer;

    private final String title;

    private final String content;

    private final LocalDateTime time;

    private final boolean owner;

    public ArticleDto(Article article) {
        this.seq = article.getSeq();
        this.userSeq = article.getUserSeq();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.time = article.getTime();
        this.owner = false;
    }

    public ArticleDto(Article article, long reqUserSeq) {
        this.seq = article.getSeq();
        this.userSeq = article.getUserSeq();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.time = article.getTime();
        this.owner = reqUserSeq == article.getUserSeq();
    }

    public long getSeq() {
        return seq;
    }

    public long getUserSeq() {
        return userSeq;
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

    public boolean isOwner() {
        return owner;
    }

}
