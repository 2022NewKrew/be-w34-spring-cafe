package com.kakao.cafe.service.article.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReplyInfo {
    private Long replyId;
    private Long articleId;
    private String writerId;
    private String writerName;
    private String comment;
    private String createdTime;
    private String updatedTime;
    private Boolean canUpdate;

    @Builder
    public ReplyInfo(Long replyId, Long articleId, String writerId, String writerName, String comment, String createdTime, String updatedTime, Boolean canUpdate) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.writerId = writerId;
        this.writerName = writerName;
        this.comment = comment;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.canUpdate = canUpdate;
    }
}
