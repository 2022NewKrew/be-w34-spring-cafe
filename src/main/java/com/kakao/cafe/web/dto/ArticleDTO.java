package com.kakao.cafe.web.dto;

public class ArticleDTO {
    private String writer;
    private String title;
    private String contents;

    public ArticleDTO(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents= contents;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContent(String contents) {
        this.contents = contents;
    }
}
