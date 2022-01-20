package com.kakao.cafe.comment.adapter.in.web.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@RequiredArgsConstructor
public class LeaveCommentRequest {

    private final String content;
    @NotNull
    private final Long questionPostId;
}
