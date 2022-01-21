package com.kakao.cafe.controller.dto.response;

import com.kakao.cafe.domain.Reply;

public class ReplyQueryResponseDto {

    private final String writerId;
    private final String comment;

    public ReplyQueryResponseDto(Reply reply) {
        writerId = reply.getWriter().getUserId();
        comment = reply.getComment();
    }
}
