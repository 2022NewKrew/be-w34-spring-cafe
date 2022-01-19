package com.kakao.cafe.dto;

public class UpdateReplyDto {

    private String writer;
    private String contents;

    public UpdateReplyDto(String writer, String contents) {
        this.writer = writer;
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public String getWriter() {
        return writer;
    }

}
