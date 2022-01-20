package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.reply.Reply;

import java.time.format.DateTimeFormatter;

public class ReplyResponseDto {
    private Long replyId;
    private Long articleId;
    private String author;
    private String comment;
    private String createdAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy년 MM월 dd일 HH:mm");

    public ReplyResponseDto(Reply reply) {
        this.replyId = reply.getId();
        this.articleId = reply.getArticleId();
        this.author = reply.getAuthor();
        this.comment = reply.getComment();
        this.createdAt = reply.getCreatedAt().format(formatter);
    }

    public String getComment() {
        return comment;
    }

    public String getAuthor() {
        return author;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Long getReplyId() {
        return replyId;
    }
}
