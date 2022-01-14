package com.kakao.cafe.model;

import lombok.Getter;

@Getter
public class Article {
    private final int id;
    private String title;
    private String content;

    public Article(int id, ArticleSaveDTO articleSaveDTO) {
        this.id = id;
        this.title = articleSaveDTO.getTitle();
        this.content = articleSaveDTO.getContent();
    }
}
