package com.kakao.cafe.dto.reply;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CreateReplyDto {
    @NotBlank(message = "댓글을 입력하세요.")
    private String comment;
    private String userId;
    private Long postId;

    @Builder
    public CreateReplyDto(String comment, String userId, Long postId) {
        this.comment = comment;
        this.userId = userId;
        this.postId = postId;
    }
}
