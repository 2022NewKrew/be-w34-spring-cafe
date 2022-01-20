package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class ArticlePostDto {
    private final String title;
    private final String contents;

    public ArticlePostDto(String title, String contents) {
        validate(title, contents);
        this.title = title;
        this.contents = contents;
    }

    private void validate(String title, String contents) {
    }
}
