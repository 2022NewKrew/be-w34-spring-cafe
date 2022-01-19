package com.kakao.cafe.dto.post;

public class PostUpdateDto {
    private String title;
    private String contents;

    public PostUpdateDto() {
    }

    public PostUpdateDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
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

    public void setContents(String contents) {
        this.contents = contents;
    }
}
