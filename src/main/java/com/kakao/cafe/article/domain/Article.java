package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.ArticleCreateDTO;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private String name; //User의 name과 동일
    private String title;
    private String contents;
    private Timestamp createdAt;
    private Long sequence;

    public Article(ArticleCreateDTO articleCreateDTO) {
        this.name = articleCreateDTO.getName();
        this.title = articleCreateDTO.getTitle();
        this.contents = articleCreateDTO.getContents();
        this.sequence = Long.valueOf(0);
    }

    //for row mapper
    public Article(String name, String title, String contents, Timestamp createdAt, long sequence) {
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.createdAt = createdAt;
        this.sequence = sequence;
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

    public Timestamp getCreatedAt(){
        return createdAt;
    }

    public Long getSequence() {
        return sequence;
    }
}
