package com.kakao.cafe.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@Getter
public class ArticleSaveDTO {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;

}
