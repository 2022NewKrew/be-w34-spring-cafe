package com.kakao.cafe.post.adapter.in.web.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class QuestionPostUpdateRequest {

    private final String title;
    private final String content;

}
