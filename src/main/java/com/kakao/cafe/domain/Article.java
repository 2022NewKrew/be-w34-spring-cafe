package com.kakao.cafe.domain;

import com.kakao.cafe.dto.ArticleDto;
import lombok.Getter;

import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
public class Article {
    private int id;
    private String author;
    private String title;
    private String content;
    private int views;
    private Date createdAt;

    public Article(int id, ArticleDto articleDto) {
        this.id = id;
        this.author = articleDto.getAuthor();
        this.title = articleDto.getTitle();
        this.content = articleDto.getContent();
        this.views = 0;
        this.createdAt = new Date();
    }

    public String getCreatedAt(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(createdAt);
    }
}
