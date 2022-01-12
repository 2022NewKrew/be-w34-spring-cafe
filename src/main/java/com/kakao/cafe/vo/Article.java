package com.kakao.cafe.vo;

import java.util.Objects;

public class Article {
    private final Long id;
    private final String writer;
    private final Long writerId;
    private final String time;
    private final String title;
    private final String contents;


    public Article(Long id, Long writerId, String writer, String title, String contents, String time) {
        this.id = id;
        this.writerId = writerId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.time = time;
    }

    public Long getWriterId() {
        return writerId;
    }

    public long getId() {
        return id;
    }


    public String getContents() {
        return contents;
    }


    public String getWriter() {
        return writer;
    }


    public String getTitle() {
        return title;
    }


    public String getTime() {
        return time;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(id, article.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
