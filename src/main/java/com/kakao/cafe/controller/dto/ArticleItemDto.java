package com.kakao.cafe.controller.dto;

public class ArticleItemDto {
    private Long id;
    private String title;
    private String writer;

    public ArticleItemDto(Long id, String title, String writer) {
        this.id = id;
        this.title = title;
        this.writer = writer;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }
}
