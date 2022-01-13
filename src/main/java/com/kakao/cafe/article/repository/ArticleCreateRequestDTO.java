package com.kakao.cafe.article.repository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticleCreateRequestDTO {
    public String title;
    public Long authorId;
    public String contents;
}
