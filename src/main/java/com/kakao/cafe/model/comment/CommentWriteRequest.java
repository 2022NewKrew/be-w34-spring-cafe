package com.kakao.cafe.model.comment;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentWriteRequest {
    @NotBlank(message = "댓글 내용을 입력해주세요.")
    private String content;

    @Builder
    public CommentWriteRequest(String content) {
        this.content = content;
    }
    
}
