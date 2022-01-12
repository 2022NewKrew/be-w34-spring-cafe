package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionRequestDto {
    private String title;
    private String writer;
    private String contents;

    public QuestionRequestDto(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }
}
