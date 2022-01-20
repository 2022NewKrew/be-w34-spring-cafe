package com.kakao.cafe.controller.articles.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReplyResponse {
    private Long replyId;
    private Long articleId;
    private String writerId;
    private String writerName;
    private String comment;
    private String createdTime;
    private Boolean canUpdate;

    @Builder
    public ReplyResponse(Long replyId, Long articleId, String writerId, String writerName, String comment, String createdTime, Boolean canUpdate) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.comment = comment;
        this.createdTime = createdTime;
        this.canUpdate = canUpdate;
    }
}
