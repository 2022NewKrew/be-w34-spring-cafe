package com.kakao.cafe.model;

import com.kakao.cafe.model.DTO.ArticleDTO;

public class Article {
    private int id;
    private String writer;
    private String title;
    private String contents;

    public Article(){
        id = 0;
        writer = "";
        title = "";
        contents = "";
    }
    public Article(int id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Article(ArticleDTO articleDTO){
        this.writer = articleDTO.getUser();
        this.title = articleDTO.getTitle();
        this.contents = articleDTO.getContent();
    }

    public Article(int id, ArticleDTO articleDTO){
        this.id = id;
        this.writer = articleDTO.getUser();
        this.title = articleDTO.getTitle();
        this.contents = articleDTO.getContent();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "Article{" +
                ", user='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", content='" + contents + '\'' +
                '}';
    }
}
