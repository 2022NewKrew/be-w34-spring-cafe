package com.kakao.cafe.interfaces.common;

public class PostDto {
    private Long id;
    private String writer;
    private String title;
    private String body;

    public PostDto(String writer, String title, String body) {
        this.writer = writer;
        this.title = title;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "PostDto{" +
                "id=" + id +
                ", writer=" + writer +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                '}';
    }
}
