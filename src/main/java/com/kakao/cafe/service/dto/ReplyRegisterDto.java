package com.kakao.cafe.service.dto;

import com.kakao.cafe.controller.dto.request.ReplyRegisterRequestDto;
import lombok.Getter;

@Getter
public class ReplyRegisterDto {

    private final Long articleId;
    private final String writerId;
    private final String comment;

    public ReplyRegisterDto(Long articleId, ReplyRegisterRequestDto replyRegisterRequestDto) {
        this.articleId = articleId;
        writerId = replyRegisterRequestDto.getWriterId();
        comment = replyRegisterRequestDto.getComment();
    }

}
