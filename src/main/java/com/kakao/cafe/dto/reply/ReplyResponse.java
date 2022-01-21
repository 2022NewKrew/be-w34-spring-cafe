package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.reply.Reply;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ReplyResponse {
    private Long id;
    private Long articleId;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;

    public ReplyResponse(Reply reply){
        this.id = reply.getId();
        this.articleId = reply.getArticleId();
        this.writer = reply.getWriter();
        this.contents = reply.getContents();
        this.createdAt = reply.getCreatedAt();
    }
}
