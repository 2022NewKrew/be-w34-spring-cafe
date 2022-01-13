package com.kakao.cafe.domain.question;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class Question {
    private int id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;
}
