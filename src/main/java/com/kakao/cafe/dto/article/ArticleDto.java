package com.kakao.cafe.dto.article;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ArticleDto {

    private int id;
    private String writer;
    @NotBlank
    private String title;
    @NotBlank
    private String contents;
    private String date;
    private String userId;
}
