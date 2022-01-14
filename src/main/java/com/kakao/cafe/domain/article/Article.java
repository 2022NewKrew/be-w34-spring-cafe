package com.kakao.cafe.domain.article;

public class Article {
    private Long index;
    private String title;
    private String content;
    private String date;
    private String writer;
    private Long view;

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

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    @Override
    public String toString() {
        return "Article{" +
                "index=" + index +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", date='" + date + '\'' +
                ", writer='" + writer + '\'' +
                ", view=" + view +
                '}';
    }
}
