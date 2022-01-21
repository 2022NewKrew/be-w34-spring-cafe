package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.reply.Reply;
import lombok.Getter;

import java.time.format.DateTimeFormatter;

@Getter
public class ReplyResponse {
    private Long replyId;
    private Long articleId;
    private String replyWriter;
    private String comments;
    private String createdAt;

    public ReplyResponse(Reply reply){
        this.replyId = reply.getId();
        this.articleId = reply.getArticleId();
        this.replyWriter = reply.getWriter();
        this.comments = reply.getContents();
        this.createdAt = reply.getCreatedAt().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        );
    }
}
