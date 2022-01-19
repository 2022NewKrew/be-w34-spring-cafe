package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Reply;

import java.sql.Timestamp;

public class ReplyViewDTO {
    private Long sequence;
    private String userId;
    private String contents;
    private Timestamp createdAt;

    public ReplyViewDTO(Reply reply){
        this.sequence = reply.getSequence();
        this.userId = reply.getUserId();
        this.contents = reply.getContents();
        this.createdAt = reply.getCreatedAt();
    }

    public Long getSequence() {
        return sequence;
    }

    public String getUserId() {
        return userId;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
