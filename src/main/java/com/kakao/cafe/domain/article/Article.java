package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.user.User;
import org.springframework.core.style.ToStringCreator;

import java.time.LocalDate;
import java.util.Objects;

public class Article {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDate createdAt;

    public Article(Long id, String author, String title, String content, LocalDate createdAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public String toString() {
        return new ToStringCreator(this)
                .append("author", this.getAuthor())
                .append("title", this.getTitle())
                .append("content", this.getContent())
                .toString();
    }
}
