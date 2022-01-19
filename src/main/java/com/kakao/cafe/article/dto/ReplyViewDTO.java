package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Reply;

import java.sql.Timestamp;

public class ReplyViewDTO {
    private Long sequence;
    private String userId;
    private String name;
    private String contents;
    private Timestamp createdAt;

    public ReplyViewDTO(Long sequence, String userId, String name, String contents, Timestamp createdAt){
        this.sequence = sequence;
        this.userId = userId;
        this.name = name;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public Long getSequence() {
        return sequence;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }
}
