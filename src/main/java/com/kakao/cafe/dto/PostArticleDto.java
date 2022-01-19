package com.kakao.cafe.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
public class PostArticleDto {

    private Long articleId;
    @NotEmpty
    private String writer;
    @NotEmpty
    private String contents;
    @NotEmpty
    private String title;
}
