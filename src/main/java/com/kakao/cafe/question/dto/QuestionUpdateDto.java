package com.kakao.cafe.question.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * 질문글 수정에대한 DTO 입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class QuestionUpdateDto {
    private String id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String contents;
}
