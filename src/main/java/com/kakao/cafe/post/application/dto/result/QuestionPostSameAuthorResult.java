package com.kakao.cafe.post.application.dto.result;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuestionPostSameAuthorResult {

    private final boolean isSame;
}
