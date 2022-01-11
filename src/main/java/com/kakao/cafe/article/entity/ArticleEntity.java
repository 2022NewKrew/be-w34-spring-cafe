package com.kakao.cafe.article.entity;


import lombok.Builder;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
public class ArticleEntity {
    private Long id;
    private String writer;
    private String title;
    private String contents;
    private String createdAt;
    private List<Object> comments = new ArrayList<>(); // todo: 미구현

    @Builder
    private ArticleEntity(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm"));
    }

    public void setId(Long id) {
        this.id = id;
    }
}
