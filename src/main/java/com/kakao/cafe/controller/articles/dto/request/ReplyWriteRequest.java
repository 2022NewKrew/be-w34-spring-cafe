package com.kakao.cafe.controller.articles.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class ReplyWriteRequest {

    private final String comment;

    @Builder
    public ReplyWriteRequest(String comment) {
        this.comment = comment;
    }
}
