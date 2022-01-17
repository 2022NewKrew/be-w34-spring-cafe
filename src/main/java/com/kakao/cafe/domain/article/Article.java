package com.kakao.cafe.domain.article;

import java.time.LocalDateTime;
import java.util.Objects;

public class Article {
    private final int id;
    private final String writer;
    private final LocalDateTime createdAt;
    private final String title;
    private final String contents;

    public Article(int id, String writer, LocalDateTime createdAt, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.createdAt = createdAt;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return id == article.id && Objects.equals(writer, article.writer) && Objects.equals(createdAt, article.createdAt) && Objects.equals(title, article.title) && Objects.equals(contents, article.contents);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, writer, createdAt, title, contents);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", createdAt=" + createdAt +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }

    public static class Builder {
        private int id;
        private String writer;
        private LocalDateTime createdAt;
        private String title;
        private String contents;

        private Builder() {}

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public Builder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public Article build() {
            return new Article(id, writer, createdAt, title, contents);
        }
    }
}
