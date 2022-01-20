package com.kakao.cafe.service.reply.dto;

public class ReplyCreateDto {

    private final int articleId;
    private final String userId;
    private final String comment;

    public ReplyCreateDto(int articleId, String userId, String comment) {
        this.articleId = articleId;
        this.userId = userId;
        this.comment = comment;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getUserId() {
        return userId;
    }

    public String getComment() {
        return comment;
    }
}
