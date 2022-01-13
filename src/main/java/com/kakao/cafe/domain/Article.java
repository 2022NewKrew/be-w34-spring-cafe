package com.kakao.cafe.domain;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    private final int parent;

    public Article(ArticleDTO articleDTO){
        this.writer = articleDTO.getWriter();
        this.title = articleDTO.getTitle();
        this.contents = articleDTO.getContents();
        this.date = LocalDateTime.now();
        this.comments = articleDTO.getComments().stream().map(Article::new).collect(Collectors.toList());
        this.id = articleDTO.getId();
        this.commentSize = articleDTO.getCommentSize();
        this.parent = articleDTO.getParent();
    }
}
