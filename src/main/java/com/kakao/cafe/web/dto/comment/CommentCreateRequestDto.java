package com.kakao.cafe.web.dto.comment;

public class CommentCreateRequestDto {
    private String writer;
    private String content;

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
