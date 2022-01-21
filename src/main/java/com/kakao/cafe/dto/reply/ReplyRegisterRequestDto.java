package com.kakao.cafe.dto.reply;

import javax.validation.constraints.NotBlank;

public class ReplyRegisterRequestDto {

    @NotBlank
    private final String comment;

    public ReplyRegisterRequestDto(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }
}
