package com.kakao.cafe.qna.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionDTO {
    private final String writer;
    private final String title;
    private final String contents;
}
