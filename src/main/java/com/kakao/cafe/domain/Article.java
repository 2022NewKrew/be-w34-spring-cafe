package com.kakao.cafe.domain;

import org.springframework.beans.factory.annotation.Autowired;

public class Article {
    private String title;
    private String content;
    private String writer;
    private Integer articleIndex;

    @Autowired
    public Article(String title, String content, String writer, Integer articleIndex) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.articleIndex = articleIndex;
    }

    public Article(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
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

    public Integer getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(Integer articleIndex) {
        this.articleIndex = articleIndex;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }
}

