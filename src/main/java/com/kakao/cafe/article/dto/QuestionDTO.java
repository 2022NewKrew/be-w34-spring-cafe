package com.kakao.cafe.article.dto;

import lombok.Getter;


@Getter
public class QuestionDTO {

    private final String title;
    private final String contents;

    public QuestionDTO(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

}
