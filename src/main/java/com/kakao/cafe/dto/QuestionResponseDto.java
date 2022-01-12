package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class QuestionResponseDto {
    private int id;
    private String title;
    private String writer;
    private String contents;
    private LocalDateTime createdDateTime;

    public QuestionResponseDto(int id, String title, String writer, String contents, LocalDateTime createdDateTime) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createdDateTime = createdDateTime;
    }
}
