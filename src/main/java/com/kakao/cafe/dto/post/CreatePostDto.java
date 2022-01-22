package com.kakao.cafe.dto.post;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreatePostDto {
    @NotNull
    private String title;
    @NotNull
    private String content;

    @Builder
    public CreatePostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
