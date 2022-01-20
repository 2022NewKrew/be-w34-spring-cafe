package com.kakao.cafe.dto.question;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Builder
@Getter
public class QuestionsResponseDto {
    private int id;
    private int userId;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
}
