package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;

import java.sql.Timestamp;
import java.util.Date;

public class ArticleViewDTO {
    private String userId;
    private String name; //User의 name과 동일
    private String title;
    private String contents;
    private Timestamp createdAt;
    private Long sequence;

    public ArticleViewDTO(Article article) {
        this.userId = article.getUserId();
        this.name = article.getName();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.createdAt = article.getCreatedAt();
        this.sequence = article.getSequence();
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getSequence() {
        return sequence;
    }
}
