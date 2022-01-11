package com.kakao.cafe.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionSaveDto {
    private String title;
    private String writer;
    private String contents;

    public QuestionSaveDto(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }
}
