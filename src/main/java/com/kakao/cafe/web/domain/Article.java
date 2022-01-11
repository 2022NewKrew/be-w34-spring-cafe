package com.kakao.cafe.web.domain;

import com.kakao.cafe.web.dto.ArticleDTO;

public class Article {
    private int id;
    private String author;
    private String title;
    private String content;

    public Article(ArticleDTO articleDTO) {
        this.id = articleDTO.getId();
        this.author = articleDTO.getWriter();
        this.title = articleDTO.getTitle();
        this.content = articleDTO.getContents();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
