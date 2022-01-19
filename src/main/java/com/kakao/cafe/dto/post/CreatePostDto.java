package com.kakao.cafe.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreatePostDto {
    private String writer;
    @NotNull
    private String title;
    @NotNull
    private String content;

    @Builder
    public CreatePostDto(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    @Override
    public String toString() {
        return "CreatePostDto{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
