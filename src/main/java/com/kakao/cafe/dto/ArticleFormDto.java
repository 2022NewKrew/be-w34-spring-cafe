package com.kakao.cafe.dto;

import lombok.Getter;

@Getter
public class ArticleFormDto {

    private final String title;
    private final String writer;
    private final String contents;

    public ArticleFormDto(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "ArticleFormDto{" +
            "title='" + title + '\'' +
            ", writer='" + writer + '\'' +
            ", contents='" + contents + '\'' +
            '}';
    }
}
