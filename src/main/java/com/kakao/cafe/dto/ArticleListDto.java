package com.kakao.cafe.dto;

import com.kakao.cafe.entity.Article;
import com.kakao.cafe.entity.User;

import java.time.LocalDate;

public class ArticleListDto {
    private int id;
    private String content;
    private String title;
    private LocalDate createdTime;
    private int views;
    private String writerName;

    public ArticleListDto(int id, String content, String title, LocalDate createdTime, int views, User writer) {
        this.id = id;
        this.content = content;
        this.title = title;
        this.createdTime = createdTime;
        this.views = views;
        this.writerName = writer.getName();
    }

    public static ArticleListDto entityToDto(Article article) {
        return new ArticleListDto(
                article.getId(),
                article.getContent(),
                article.getTitle(),
                article.getCreatedTime(),
                article.getViews(),
                article.getWriter()
        );
    }

}
