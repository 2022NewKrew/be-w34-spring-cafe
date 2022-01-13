package com.kakao.cafe.article.domain;

import com.kakao.cafe.article.dto.ArticleCreateDTO;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private String name; //User의 name과 동일
    private String title;
    private String contents;
    private Date date;
    private Long sequence;

    public Article(ArticleCreateDTO articleCreateDTO, Long sequence) {
        this.name = articleCreateDTO.getName();
        this.title = articleCreateDTO.getTitle();
        this.contents = articleCreateDTO.getContents();
        this.date = new Date();
        this.sequence = sequence;
    }

    public Article(String name, String title, String contents, java.sql.Date date, long sequence) {
        this.name = name;
        this.title = title;
        this.contents = contents;
        this.date = date;
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

    public Date getDate(){
        return date;
    }

    public Long getSequence() {
        return sequence;
    }
}
