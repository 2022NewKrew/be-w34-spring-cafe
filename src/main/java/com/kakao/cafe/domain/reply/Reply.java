package com.kakao.cafe.domain.reply;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Reply {
    private Long id;
    private Long articleId;
    private Long replyerId;
    private String content;
    private LocalDateTime createdAt;
}
