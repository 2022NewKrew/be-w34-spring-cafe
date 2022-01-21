package com.kakao.cafe.dto.reply;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class ReplyUpdateRequest {
    private Long id;
    private Long articleId;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
}
