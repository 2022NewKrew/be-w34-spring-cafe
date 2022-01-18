package com.kakao.cafe.domain.article;

public class Article {

    private final Long id;
    private final Long authorId;
    private String author;
    private String title;
    private String description;

    public Article(Long authorId, String title, String description) {
        this(null, authorId, null, title, description);
    }

    public Article(Long id, Long authorId, String author, String title, String description) {
        this.id = id;
        this.authorId = authorId;
        this.author = author;
        this.title = title;
        this.description = description;
    }

    public Long getId() {
        if (id == null) {
            throw new AssertionError("id 값이 설정되지 않은 엔티티입니다.");
        }
        return id;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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
        if (this == obj) {
            return true;
        }
        if (obj instanceof Article) {
            return this.id.equals(((Article) obj).id);
        }
        return false;
    }
}
