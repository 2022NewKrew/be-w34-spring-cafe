package com.kakao.cafe.reply.service.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
public class OneReplyServiceResponse {
    private Long id;
    private String authorStringId;
    private String contents;
    private LocalDateTime writeTime;

    @Builder
    private OneReplyServiceResponse(Long id, String authorStringId, String contents, LocalDateTime writeTime) {
        this.id = id;
        this.authorStringId = authorStringId;
        this.contents = contents;
        this.writeTime = writeTime;
    }
}
