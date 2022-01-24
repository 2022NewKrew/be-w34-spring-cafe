package com.kakao.cafe.dto.reply;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateReplyDto {
    @NotBlank(message = "댓글을 입력하세요.")
    private String comment;

    @Builder
    public CreateReplyDto(String comment) {
        this.comment = comment;
    }
}
