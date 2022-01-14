package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class PostArticleDto {

    @NotNull
    private String writer;
    @NotNull
    private String contents;
    @NotNull
    private String title;
}
