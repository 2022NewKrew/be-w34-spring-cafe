package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Article;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@Getter
public class ArticleDTO {
    private final long id;
    private final String title;
    private final String content;
    private final String createUserId;
    private final String createDate;
    private final int views;

    public ArticleDTO(String title, String content) {
        this.id = 0;
        this.title = title;
        this.content = content;
        this.createUserId = "unknown";
        this.createDate = LocalDate.now().toString();
        this.views = 0;
    }

    private ArticleDTO(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.createUserId = article.getCreateUserId();
        this.createDate = article.getCreateDate();
        this.views = article.getViews();
    }

    public static ArticleDTO newInstance(Article article) {
        return new ArticleDTO(article);
    }

}
