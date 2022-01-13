package com.kakao.cafe.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private final int commentSize;
    private final List<Article> comments;

    public Article(ArticleDTO articleDTO){
        this.writer = articleDTO.getWriter();
        this.title = articleDTO.getTitle();
        this.contents = articleDTO.getContents();
        this.date = LocalDateTime.now();
        this.comments = new ArrayList<>();
        this.id = articleDTO.getId();
        this.commentSize = 0;
    }
}
