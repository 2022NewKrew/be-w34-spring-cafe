package com.kakao.cafe.domain.article;

import java.time.LocalDateTime;

public class Article {

    private final int index;
    private final String writer;
    private final String title;
    private final String contents;
    private final LocalDateTime postedDate;

    public Article(int index, String writer, String title, String contents, LocalDateTime postedDate) {
        this.index = index;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.postedDate = postedDate;
    }

    public int getIndex() {
        return index;
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

    public LocalDateTime getPostedDate() {
        return postedDate;
    }
}
