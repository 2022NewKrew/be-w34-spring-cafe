package com.kakao.cafe.domain.article;

public class Article {

    private Long id;
    private String title;
    private String description;

    public Article(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public boolean isEqualUserId(long id) {
        return this.id == id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Article) {
            return isEqualUserId(((Article) obj).id);
        }
        return false;
    }
}
