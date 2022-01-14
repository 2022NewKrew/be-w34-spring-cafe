package com.kakao.cafe.model;

import java.util.Objects;

public class Article {
    private final int id;
    private String title;
    private String content;

    public Article(int id, ArticleSaveDTO articleSaveDTO) {
        validateArticleSaveDTO(articleSaveDTO);

        this.id = id;
        this.title = articleSaveDTO.getTitle();
        this.content = articleSaveDTO.getContent();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    private void validateArticleSaveDTO(ArticleSaveDTO articleSaveDTO){
        validateTitle(articleSaveDTO.getTitle());
        validateContent(articleSaveDTO.getContent());
    }

    private void validateTitle(String title){
        if(Objects.isNull(title)) throw new IllegalArgumentException();
    }

    private void validateContent(String content){
        if(Objects.isNull(content)) throw new IllegalArgumentException();
    }
}
