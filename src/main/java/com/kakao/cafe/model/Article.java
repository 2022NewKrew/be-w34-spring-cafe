package com.kakao.cafe.model;

import com.kakao.cafe.utility.NullChecker;

import java.time.LocalDateTime;

public class Article {
    private final int id;
    private final String title;
    private final String writer;
    private final LocalDateTime createDate;
    private final String contents;

    public Article(int id, String title, String writer, String contents) {
        NullChecker.checkNotNull(title);
        NullChecker.checkNotNull(writer);
        NullChecker.checkNotNull(contents);

        this.id = id;
        this.title = title;
        this.writer = writer;
        this.createDate = LocalDateTime.now();
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public String getContents() {
        return contents;
    }
}
