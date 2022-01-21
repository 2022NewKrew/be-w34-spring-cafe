package com.kakao.cafe.service.reply.dto;

import com.kakao.cafe.model.reply.Reply;
import java.time.LocalDateTime;

public class ReplyDto {

    private final int id;
    private final int articleId;
    private final String userId;
    private final String comment;
    private final LocalDateTime createDate;

    public ReplyDto(Reply reply) {
        this.id = reply.getId();
        this.articleId = reply.getArticleId();
        this.userId = reply.getUserId().getValue();
        this.comment = reply.getComment().getValue();
        this.createDate = reply.getCreateDate();
    }

    public int getId() {
        return id;
    }
}
