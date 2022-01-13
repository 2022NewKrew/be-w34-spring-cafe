package com.kakao.cafe.dto.question;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class QuestionResponseDto {
    private int id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
}
