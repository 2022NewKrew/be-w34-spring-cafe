package com.kakao.cafe.dto.question;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class QuestionSaveDto {
    private int userId;
    private String title;
    private String contents;
}
