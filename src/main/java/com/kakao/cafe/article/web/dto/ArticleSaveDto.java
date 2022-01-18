package com.kakao.cafe.article.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleSaveDto {

    private String writer;

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Contents is mandatory")
    private String contents;
}
