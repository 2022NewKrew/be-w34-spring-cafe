package com.kakao.cafe.article.dto.request;

import org.springframework.lang.NonNull;

public class ArticleCreateRequest {

    @NonNull
    private final String writer;
    @NonNull
    private final String title;
    @NonNull
    private final String contents;

    public ArticleCreateRequest(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() { return this.writer; }
    public String getTitle() { return this.title; }
    public String getContents() { return this.contents; }
}
