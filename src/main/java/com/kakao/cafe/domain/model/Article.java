package com.kakao.cafe.domain.model;

import com.kakao.cafe.domain.dto.ArticleSaveDTO;
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
