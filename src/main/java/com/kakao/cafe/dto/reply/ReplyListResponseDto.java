package com.kakao.cafe.dto.reply;

public class ReplyListResponseDto {
    private final String replyId;
    private final String writer;
    private final String comment;
    private final String createdAt;

    public ReplyListResponseDto(String replyId, String writer, String comment, String createdAt) {
        this.replyId = replyId;
        this.writer = writer;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public String getReplyId() {
        return replyId;
    }

    public String getWriter() {
        return writer;
    }

    public String getComment() {
        return comment;
    }

    public String getCreatedAt() {
        return createdAt;
    }
}
