package com.kakao.cafe.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostDto {
    private String writer;
    private String title;
    private String content;

    @Builder
    public CreatePostDto(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
