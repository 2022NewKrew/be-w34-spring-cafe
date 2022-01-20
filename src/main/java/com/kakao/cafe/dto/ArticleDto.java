package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;

import java.time.LocalDate;

public class ArticleDto {
    private int id;
    private String content;
    private String title;
    private LocalDate createdTime;
    private int views;
    private User writer;

    public ArticleDto(int id, String content, String title, LocalDate createdTime, int views, User writer) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.createdTime = createdTime;
        this.views = views;
        this.writer = writer;
    }

    public static ArticleDto entityToDto(Article article) {
        if (article == null) return null;
        return new ArticleDto(
                article.getId(),
                article.getContent(),
                article.getTitle(),
                article.getCreatedTime(),
                article.getViews(),
                article.getWriter()
        );
    }

    public User getWriter() {
        return writer;
    }
}
