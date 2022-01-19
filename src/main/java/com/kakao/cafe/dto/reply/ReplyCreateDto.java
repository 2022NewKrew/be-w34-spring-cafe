package com.kakao.cafe.dto.reply;

public class ReplyCreateDto {
    private String contents;

    public ReplyCreateDto(String contents) {
        this.contents = contents;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
