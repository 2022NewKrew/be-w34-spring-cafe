package com.kakao.cafe.dto.answer;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AnswerSaveDto {
    private int userId;
    private int questionId;
    private String contents;
}
