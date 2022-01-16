package com.kakao.cafe.domain;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class Article {

    private int id;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime date;

    public Article(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Article(int id, String title, String content, String writer, LocalDateTime date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void generateId(int i) {
        this.id = i;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", writer='" + writer + '\'' +
                ", date=" + date +
                '}';
    }


}
