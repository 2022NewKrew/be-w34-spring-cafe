package com.kakao.cafe.controller.dto.response;

import com.kakao.cafe.domain.Reply;

public class ReplyQueryResponseDto {

    private final Long id;
    private final String writerId;
    private final String comment;

    public ReplyQueryResponseDto(Reply reply) {
        id = reply.getId();
        writerId = reply.getWriterId();
        comment = reply.getComment();
    }
}
