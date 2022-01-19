package com.kakao.cafe.domain.reply.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReplyUpdateForm {
    Long id;
    Long articleId;
    String content;
}
