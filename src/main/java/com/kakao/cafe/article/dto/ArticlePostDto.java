package com.kakao.cafe.article.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePostDto {
    private Long id;
    private String author;
    private String title;
    private String contents;
    private String uploadTime;
}
