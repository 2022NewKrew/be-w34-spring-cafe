package com.kakao.cafe.reply.dto;

import com.kakao.cafe.reply.Reply;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ReplyCreateDto {
    String comment;

    public static Reply toEntity(ReplyCreateDto replyCreateDto) {
        Reply reply = new Reply();
        reply.setComment(replyCreateDto.getComment());
        return reply;
    }
}
