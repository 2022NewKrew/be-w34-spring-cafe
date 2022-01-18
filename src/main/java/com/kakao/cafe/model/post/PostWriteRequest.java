package com.kakao.cafe.model.post;

import lombok.*;

import javax.validation.constraints.NotBlank;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostWriteRequest {
    @NotBlank(message = "게시글 제목은 필수 입력 값 입니다.")
    private String title;
    @NotBlank(message = "게시글 내용은 필수 입력 값 입니다.")
    private String content;

    @Builder
    public PostWriteRequest(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
