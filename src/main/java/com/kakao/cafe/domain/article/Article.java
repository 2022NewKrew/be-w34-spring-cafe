package com.kakao.cafe.domain.article;

public class Article {

    private Long id;
    private String title;
    private String description;

    public Article(ArticleRequest articleRequest) {
        this.title = articleRequest.getTitle();
        this.description = articleRequest.getDescription();
    }

    public ArticleDto toDto() {
        return new ArticleDto(id, title, description);
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isEqualUserId(long id) {
        return this.id == id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article) {
            return isEqualUserId(((Article) obj).id);
        }
        return false;
    }
}
