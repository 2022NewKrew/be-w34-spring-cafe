package com.kakao.cafe.domain.article;

public class ArticleDto {

    private final Long id;
    private final String title;
    private final String description;

    public ArticleDto(Long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
}
