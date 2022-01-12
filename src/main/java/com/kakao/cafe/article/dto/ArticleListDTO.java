package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;

import java.util.Date;

public class ArticleListDTO {
    private String name; //User의 name과 동일
    private String title;
    private Date date;
    private Long sequence;

    public ArticleListDTO(Article article) {
        this.name = article.getName();
        this.title = article.getTitle();
        this.date = article.getDate();
        this.sequence = article.getSequence();
    }

    public String getName() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public Date getDate() {
        return date;
    }

    public Long getSequence() {
        return sequence;
    }
}
