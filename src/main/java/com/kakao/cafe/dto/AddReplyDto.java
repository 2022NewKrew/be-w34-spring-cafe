package com.kakao.cafe.dto;

public class AddReplyDto {

    private String contents;

    public AddReplyDto(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

}
