package com.kakao.cafe.domain.article;

public class Article {

    private Long id;
    private String title;
    private String description;

    public Article(ArticleRequest articleRequest) {
        this.title = articleRequest.getTitle();
        this.description = articleRequest.getDescription();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEqualUserId(long id) {
        return this.id == id;
    }
}
