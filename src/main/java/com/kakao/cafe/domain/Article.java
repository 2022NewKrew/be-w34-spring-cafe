package com.kakao.cafe.domain;

import com.kakao.cafe.dto.SampleArticleForm;


public class Article {

    private Long articleID;
    private String author;
    private String title;
    private String content;

    private Article(String author, String title, String content) {
        this.author = author;
        this.title = title;
        this.content = content;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public Long getArticleID() {
        return articleID;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void update(SampleArticleForm form){
        this.title = form.getTitle();
        this.content = form.getContent();
    }

    public static Article add(String author, SampleArticleForm form){
        return new Article(author, form.getTitle(), form.getContent());
    }
}
