package com.kakao.cafe.article.web.dto;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleModifyDto {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "Contents is mandatory")
    private String contents;
}
