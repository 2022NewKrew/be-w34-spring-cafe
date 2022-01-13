package com.kakao.cafe.dto;

public class PostDto {

    private long id = -1;
    private String writer;
    private String title;
    private String contents;

    public PostDto() {

    }

    public PostDto(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public PostDto(String writer, String title, String contents, long id) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.id = id;
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

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PostModel{" +
                "id=" + id +
                ", writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
