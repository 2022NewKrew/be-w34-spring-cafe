package com.kakao.cafe.dto.article;

import lombok.Getter;

@Getter
public class ArticleAddRequestDto {
    private final String writer;
    private final String title;
    private final String contents;

    ArticleAddRequestDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }
}
