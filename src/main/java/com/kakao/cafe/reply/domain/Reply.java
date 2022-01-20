package com.kakao.cafe.reply.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Reply {

    private Long id;
    private String content;
    private LocalDateTime createDateTime;
    private Long articleId;
    private Long memberId;
    private Boolean deleted;

    public void setId(Long id) {
        this.id = id;
    }
}
