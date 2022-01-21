package com.kakao.cafe.reply.dto;

import com.kakao.cafe.reply.domain.Reply;
import lombok.Getter;

@Getter
public class ReplyViewDTO {

    private final Long id;
    private final String writer;
    private final String contents;
    private final String writingTime;

    public ReplyViewDTO(Reply reply) {
        this.id = reply.getId();
        this.writer = reply.getWriter();
        this.contents = reply.getContents();
        this.writingTime = reply.getWritingTime();
    }
}
