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

    @Builder
    public UpdatePostDto(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
