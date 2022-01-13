package com.kakao.cafe.article.entity;


import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ArticleEntity {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private LocalDateTime createdAt;
    private List<Object> comments = new ArrayList<>(); // todo: 미구현

    @Builder
    private ArticleEntity(Long id, String writer, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
