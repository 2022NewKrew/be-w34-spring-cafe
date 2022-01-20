package com.kakao.cafe.controller.articles.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ReplyListResponse {
    private Integer length;
    private List<ReplyResponse> replies;

    @Builder
    public ReplyListResponse(Integer length, List<ReplyResponse> replies) {
        this.length = length;
        this.replies = replies;
    }
}
