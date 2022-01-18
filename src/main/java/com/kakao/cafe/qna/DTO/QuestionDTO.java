package com.kakao.cafe.qna.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class QuestionDTO {
    String writer;
    String title;
    String content;
}
