package com.kakao.cafe.entity;

import com.kakao.cafe.dto.ReplyRequestDto;

import java.time.LocalDateTime;

public class Reply {
    private int replyId;
    private User user;
    private LocalDateTime createdTime;
    private String content;
    private int articleId;

    public Reply(ReplyRequestDto replyRequestDto, User user, int articleId) {
        this.user = user;
        this.createdTime = LocalDateTime.now();
        this.content = replyRequestDto.getContent();
        this.articleId = articleId;
    }

    public Reply(int replyId, User user, LocalDateTime createdTime, String content, int articleId) {
        this.replyId = replyId;
        this.user = user;
        this.createdTime = createdTime;
        this.content = content;
        this.articleId = articleId;
    }

    public int getReplyId() {
        return replyId;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public String getContent() {
        return content;
    }

    public int getArticleId() {
        return articleId;
    }
}
