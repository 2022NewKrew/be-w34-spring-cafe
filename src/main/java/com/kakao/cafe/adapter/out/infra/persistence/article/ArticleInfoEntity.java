package com.kakao.cafe.adapter.out.infra.persistence.article;

import com.kakao.cafe.domain.article.Article;
import java.time.LocalDateTime;

public class ArticleInfoEntity {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime createdAt;

    public ArticleInfoEntity(int id, String writer, String title, String contents, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
    }

    static ArticleInfoEntity from(Article article) {
        return new ArticleInfoEntity(
            article.getId(),
            article.getWriter(),
            article.getTitle(),
            article.getContents(),
            article.getCreatedAt()
        );
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
