package com.kakao.cafe.question.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * 질문글 리스트에 대한 DTO 입니다.
 *
 * @author jm.hong
 */
@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createTime;
}
