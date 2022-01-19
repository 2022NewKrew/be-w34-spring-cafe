package com.kakao.cafe.dto.post;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdatePostDto {
    @NotNull
    private String title;
    @NotNull
    private String content;
    private String writer;

    @Builder
    public UpdatePostDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }


}
