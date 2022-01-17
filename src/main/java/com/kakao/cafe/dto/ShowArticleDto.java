package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;

public class ShowArticleDto {
    private final long id;
    private final String writeTime;
    private final String writer;
    private final String title;
    private final String contents;
    private final String span;

    public ShowArticleDto(Article article) {
        id = article.getId();
        writeTime = article.writeTimeToStr();
        writer = article.getWriter();
        title = article.getTitle();
        contents = article.getContents();
        span = article.spanTimeToStr();
    }

    public long getId() {
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

    public String getWriteTime() {
        return writeTime;
    }
}
