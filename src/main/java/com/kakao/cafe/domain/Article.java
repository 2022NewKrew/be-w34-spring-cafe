package com.kakao.cafe.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Article {
    private String name; //User의 name과 동일
    private String title;
    private String contents;
    private Date date;

    public Article(String writer, String title, String contents) {
        this.name = writer;
        this.title = title;
        this.contents = contents;
        this.date = new Date();
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

    public String getDate(){
        return date.toString();
    }
}
