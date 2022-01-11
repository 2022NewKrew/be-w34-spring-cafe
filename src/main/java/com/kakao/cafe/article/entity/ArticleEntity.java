package com.kakao.cafe.article.entity;


import lombok.Builder;
import lombok.Getter;

@Getter
public class ArticleEntity {
    private Long id;
    private String writer;
    private String title;
    private String contents;

    @Builder
    private ArticleEntity(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
