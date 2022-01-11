package com.kakao.cafe.vo;

public class ArticleVo {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;
    private final String date;

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
