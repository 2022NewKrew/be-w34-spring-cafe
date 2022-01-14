package com.kakao.cafe.dto;

import com.kakao.cafe.model.Article;


import java.time.format.DateTimeFormatter;

public class ArticleShowDto {
    private Integer id;
    private String writer;
    private String title;
    private String contents;
    private String createTime;

    public ArticleShowDto(Article article){
        this.id = article.getId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.createTime = article.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
    }
}
