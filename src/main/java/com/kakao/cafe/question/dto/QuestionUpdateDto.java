package com.kakao.cafe.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
public class QuestionUpdateDto {
    @NotEmpty
    private String title;
    @NotEmpty
    private String contents;
}
