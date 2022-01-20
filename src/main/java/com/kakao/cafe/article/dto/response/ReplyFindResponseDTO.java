package com.kakao.cafe.article.dto.response;

import java.time.format.DateTimeFormatter;

import lombok.Getter;

import com.kakao.cafe.article.entity.Reply;

@Getter
public class ReplyFindResponseDTO {
    private final int id;
    private final String writer;
    private final String contents;
    private final String createTime;

    public ReplyFindResponseDTO(Reply reply) {
        this.id = reply.getId();
        this.writer = reply.getWriter();
        this.contents = reply.getContents();
        this.createTime = reply.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
