package com.kakao.cafe.domain;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Article {
    private Long id;
    private String writer;
    private String title;
    private String contents;

    public Article(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static Article of(String writer, String title, String contents) {
        return new Article(writer, title, contents);
    }

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
