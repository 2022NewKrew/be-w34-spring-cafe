package com.kakao.cafe.dto.question;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionRequestDto {
    private String title;
    private String writer;
    private String contents;
}
