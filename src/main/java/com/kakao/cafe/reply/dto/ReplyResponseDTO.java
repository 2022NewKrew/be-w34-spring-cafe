package com.kakao.cafe.reply.dto;

import com.kakao.cafe.reply.domain.Reply;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Getter
public class ReplyResponseDTO {

    private Long id;
    private String content;
    private LocalDateTime createDateTime;
    private String writer;

    public ReplyResponseDTO(Reply reply, String writer) {
        this.id = reply.getId();
        this.content = reply.getContent();
        this.createDateTime = reply.getCreateDateTime();
        this.writer = writer;
    }
}
