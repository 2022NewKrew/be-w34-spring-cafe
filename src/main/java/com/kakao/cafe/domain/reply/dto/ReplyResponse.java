package com.kakao.cafe.domain.reply.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReplyResponse {
    private Long id;
    private String replyerName;
    private String content;
    private LocalDateTime createdAt;
}
