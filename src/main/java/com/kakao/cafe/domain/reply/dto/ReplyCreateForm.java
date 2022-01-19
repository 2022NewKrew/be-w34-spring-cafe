package com.kakao.cafe.domain.reply.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ReplyCreateForm {
    private Long articleId;
    private Long replyerId;
    private String content;
}
