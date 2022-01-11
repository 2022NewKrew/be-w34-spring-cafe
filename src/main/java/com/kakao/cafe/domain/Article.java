package com.kakao.cafe.domain;

public class Article {
    private String name; //User의 name과 동일
    private String title;
    private String contents;

    public Article(String writer, String title, String contents) {
        this.name = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() {
        return name;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }
}
