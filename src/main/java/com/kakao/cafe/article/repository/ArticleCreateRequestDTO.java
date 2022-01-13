package com.kakao.cafe.article.repository;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleCreateRequestDTO {
    public String title;
    public Long authorId;
    public String contents;
}
