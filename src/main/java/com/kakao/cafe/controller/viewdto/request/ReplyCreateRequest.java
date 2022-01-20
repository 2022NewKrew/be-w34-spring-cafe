package com.kakao.cafe.controller.viewdto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ReplyCreateRequest {
    private final String answer;
    private final String articleId;
}
