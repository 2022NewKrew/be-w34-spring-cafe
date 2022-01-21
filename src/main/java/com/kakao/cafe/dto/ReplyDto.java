package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Reply;

import java.time.LocalDateTime;

public class ReplyDto {
    private final int replyId;
    private final String writerName;
    private final String content;
    private final LocalDateTime createdTime;
    private final int articleId;

    public ReplyDto(int replyId, String writerName, String content, LocalDateTime createdTime, int articleId) {
        this.replyId = replyId;
        this.writerName = writerName;
        this.content = content;
        this.createdTime = createdTime;
        this.articleId = articleId;
    }

    public static ReplyDto entityToDto(Reply reply) {
        if (reply == null) return null;
        return new ReplyDto(
                reply.getReplyId(),
                reply.getUser().getName(),
                reply.getContent(),
                reply.getCreatedTime(),
                reply.getArticleId()
        );
    }
}
