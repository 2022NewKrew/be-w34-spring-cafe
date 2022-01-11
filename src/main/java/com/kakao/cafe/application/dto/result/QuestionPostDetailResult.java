package com.kakao.cafe.application.dto.result;

import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
public class QuestionPostDetailResult {

    private final Long questionPostId;
    private final String title;
    private final String content;
    private final String date;
    private final int viewCount;
    private final String author;

}
