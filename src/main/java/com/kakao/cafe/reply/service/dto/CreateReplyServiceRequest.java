package com.kakao.cafe.reply.service.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateReplyServiceRequest {
    private Long articleId;
    private Long authorId;
    private String authorStringId;
    private String contents;

    @Builder
    private CreateReplyServiceRequest(Long articleId, Long authorId, String authorStringId, String contents) {
        this.articleId = articleId;
        this.authorId = authorId;
        this.authorStringId = authorStringId;
        this.contents = contents;
    }
}
