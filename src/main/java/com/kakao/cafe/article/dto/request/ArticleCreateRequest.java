package com.kakao.cafe.article.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class ArticleCreateRequest {

    @NotNull
    @NotBlank
    private final String writer;
    @NotNull
    @NotBlank
    private final String title;
    @NotNull
    @NotBlank
    private final String contents;
}
