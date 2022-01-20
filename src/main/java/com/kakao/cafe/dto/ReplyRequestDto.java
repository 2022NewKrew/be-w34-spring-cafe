package com.kakao.cafe.dto;

public class ReplyRequestDto {
    private String content;

    public ReplyRequestDto(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
