package com.kakao.cafe.domain;

import lombok.Getter;

import java.time.LocalDateTime;

/**
 * author    : brody.moon
 * version   : 1.0
 * Article 정보 클래스입니다.
 */

@Getter
public class Article {
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime date;
    private final int id;
    private final int parent;

    public Article(ArticleDTO articleDTO){
        this.writer = articleDTO.getWriter();
        this.title = articleDTO.getTitle();
        this.contents = articleDTO.getContents();
        this.date = articleDTO.getDate();
        this.id = articleDTO.getId();
        this.parent = articleDTO.getParent();
    }
}
