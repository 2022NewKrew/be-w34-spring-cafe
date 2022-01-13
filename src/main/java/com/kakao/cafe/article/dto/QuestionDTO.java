package com.kakao.cafe.article.dto;

import lombok.Getter;


@Getter
public class QuestionDTO {

    private final String writer;
    private final String title;
    private final String contents;

    public QuestionDTO(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

}
