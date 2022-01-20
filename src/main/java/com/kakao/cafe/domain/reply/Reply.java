package com.kakao.cafe.domain.reply;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Reply {
    private Long id;
    private Long articleId;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
    private Boolean deleted;
}
