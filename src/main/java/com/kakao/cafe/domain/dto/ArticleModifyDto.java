package com.kakao.cafe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class ArticleModifyDto {

    @Setter
    private int id;

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;
}
