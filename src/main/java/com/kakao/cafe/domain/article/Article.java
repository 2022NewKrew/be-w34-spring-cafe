package com.kakao.cafe.domain.article;

public class Article {
    private Long index;
    private final String title;
    private final String content;
    private String date;
    private final String writer;
    private Long view;

    public Article(Long index, String title, String content, String writer) {
        this.index = index;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.view = 0L;
    }

    public void addView() {
        this.view++;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public Long getView() {
        return view;
    }
}
