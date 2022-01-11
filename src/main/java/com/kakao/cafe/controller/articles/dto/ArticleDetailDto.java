package com.kakao.cafe.controller.articles.dto;

public class ArticleDetailDto {
    private String title;
    private String writer;
    private String contents;

    public ArticleDetailDto(String title, String writer, String contents) {
        this.title = title;
        this.writer = writer;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    @Override
    public String toString() {
        return "ArticleDetailDto{" +
                "title='" + title + '\'' +
                ", writer='" + writer + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
