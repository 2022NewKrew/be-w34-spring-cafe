package com.kakao.cafe.post.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class QuestionPostDetailResult {

    private final Long questionPostId;
    private final String title;
    private final String content;
    private final String date;
    private final int viewCount;
    private final String author;

}
