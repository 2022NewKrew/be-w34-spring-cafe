package com.kakao.cafe.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 질문글 생성폼에대한 DTO입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class QuestionCreateDto {
    @NotEmpty
    private String writer;
    @NotEmpty
    private String title;
    @NotEmpty
    private String contents;
}
