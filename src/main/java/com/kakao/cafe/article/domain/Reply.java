package com.kakao.cafe.article.domain;

import java.sql.Timestamp;

public class Reply {
    private Long sequence;
    private String userId;
    private Long articleSeq;
    private String contents;
    private Timestamp createdAt;

    public Reply(Long sequence, String userId, Long articleSeq, String contents, Timestamp createdAt) {
        this.sequence = sequence;
        this.userId = userId;
        this.articleSeq = articleSeq;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public Long getSequence() {
        return sequence;
    }

    public String getUserId() {
        return userId;
    }

    public Long getArticleSeq() {
        return articleSeq;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
