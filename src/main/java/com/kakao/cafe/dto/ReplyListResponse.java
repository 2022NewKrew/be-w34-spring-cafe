package com.kakao.cafe.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReplyListResponse {
    Long replyId;
    Long questionId;
    Long userId;
    String writer;
    String createdDateTime;
    String contents;

    @Builder
    public ReplyListResponse(Long replyId, Long questionId, Long userId, String writer, String createdDateTime, String contents) {
        this.replyId = replyId;
        this.questionId = questionId;
        this.userId = userId;
        this.writer = writer;
        this.createdDateTime = createdDateTime;
        this.contents = contents;
    }
}
