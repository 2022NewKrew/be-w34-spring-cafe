package com.kakao.cafe.question.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionDto {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createTime;
}
