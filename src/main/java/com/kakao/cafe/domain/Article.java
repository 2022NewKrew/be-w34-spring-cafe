package com.kakao.cafe.domain;

import com.kakao.cafe.controller.dto.ArticleSaveForm;
import com.kakao.cafe.repository.dto.ArticleResult;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;


@Getter
@Setter
public class Article {
    private Long articleId;

    private String writer;
    private String title;
    private String content;
    private LocalDateTime createdAt;
    private Long numOfComment;

    public Article() {}

    public static Article from(ArticleSaveForm dto) {
        return new Article()
                .setContent(dto.getContent())
                .setTitle(dto.getTitle())
                .setWriter(dto.getWriter())
                .setCreatedAt(LocalDateTime.now())
                .setNumOfComment(0L);
    }

    public static Article from(ArticleResult dto) {
        return new Article()
                .setArticleId(dto.getPostId())
                .setContent(dto.getContent())
                .setTitle(dto.getTitle())
                .setWriter(dto.getWriter())
                .setCreatedAt(LocalDateTime.now())
                .setNumOfComment(dto.getNumOfComment());
    }

    public Article setArticleId(Long articleId) {
        this.articleId = articleId;
        return this;
    }

    public Article setWriter(String writer) {
        this.writer = writer;
        return this;
    }

    public Article setTitle(String title) {
        this.title = title;
        return this;
    }

    public Article setContent(String content) {
        this.content = content;
        return this;
    }

    public Article setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public Article setNumOfComment(Long numOfComment) {
        this.numOfComment = numOfComment;
        return this;
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleId, writer, title, content, createdAt, numOfComment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof User))
            return false;
        Article article = (Article) obj;

        return Objects.equals(writer, article.getWriter()) &&
                Objects.equals(createdAt, article.getCreatedAt()) &&
                Objects.equals(content, article.getContent()) &&
                Objects.equals(numOfComment, article.getNumOfComment());
    }
}
