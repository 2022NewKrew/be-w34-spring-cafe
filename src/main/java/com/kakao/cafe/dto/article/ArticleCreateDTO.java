package com.kakao.cafe.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
public class ArticleCreateDTO {

    @NotBlank
    private String authorId;

    @NotBlank
    private String title;

    private String contents;
}