package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class ArticleResponseDto {
    private Long id;
    private String author;
    private String title;
    private String content;
    private LocalDate createdAt;

    private ArticleResponseDto(Long id, String author, String title, String content, LocalDate createdAt) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public static ArticleResponseDto of(Article article) {
        return new ArticleResponseDto(article.getId(), article.getAuthor(), article.getTitle(), article.getContent(), article.getCreatedAt());
    }

    public static List<ArticleResponseDto> of(List<Article> articles) {
        return articles.stream()
                .map(ArticleResponseDto::of)
                .collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }
}
