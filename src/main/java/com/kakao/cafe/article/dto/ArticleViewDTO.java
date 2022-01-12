package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;

import java.util.Date;

public class ArticleViewDTO {
    private String name; //User의 name과 동일
    private String title;
    private String contents;
    private Date date;

    public ArticleViewDTO(Article article) {
        this.name = article.getName();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.date = article.getDate();
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

    public Date getDate() {
        return date;
    }
}
