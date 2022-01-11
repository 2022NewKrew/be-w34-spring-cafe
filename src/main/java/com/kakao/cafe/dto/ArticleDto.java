package com.kakao.cafe.dto;

public class ArticleDto {
    private int id;
    private String writer;
    private String title;
    private String contents;

    public ArticleDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() { return writer; }

    public void setId(int id) { this.id = id; }
}
