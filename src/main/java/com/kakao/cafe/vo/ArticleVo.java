package com.kakao.cafe.vo;

public class ArticleVo {

    private int id;
    private String writer;
    private String title;
    private String contents;
    private String date;

    public ArticleVo(){}

    public ArticleVo(int id, String writer, String title, String contents, String date) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getDate() {
        return date;
    }
}
