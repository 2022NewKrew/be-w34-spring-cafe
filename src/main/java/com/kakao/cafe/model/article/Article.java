package com.kakao.cafe.model.article;

import java.time.LocalDateTime;

public class Article {

    private final int id;
    private Title title;
    private final Writer writer;
    private Contents contents;
    private final LocalDateTime createDate;

    public Article(int id, Title title, Writer writer, Contents contents,
            LocalDateTime createDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.contents = contents;
        this.createDate = createDate;
    }

    public Article(int id, Title title, Writer writer, Contents contents) {
        this(id, title, writer, contents, LocalDateTime.now());
    }

    public int getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public Writer getWriter() {
        return writer;
    }

    public Contents getContents() {
        return contents;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setContents(Contents contents) {
        this.contents = contents;
    }
}
