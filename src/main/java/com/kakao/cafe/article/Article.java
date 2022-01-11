package com.kakao.cafe.article;

import org.springframework.lang.NonNull;

public class Article {

    @NonNull
    private String writer;
    @NonNull
    private String title;
    @NonNull
    private String contents;

    private String time;

    public Article(ArticleRequest articleRequest) {
        this.writer = articleRequest.getWriter();
        this.title = articleRequest.getTitle();
        this.contents = articleRequest.getContents();
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
