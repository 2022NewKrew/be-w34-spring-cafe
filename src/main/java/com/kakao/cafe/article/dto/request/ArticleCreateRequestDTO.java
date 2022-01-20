package com.kakao.cafe.article.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.kakao.cafe.article.entity.Article;

@AllArgsConstructor
@Getter
public class ArticleCreateRequestDTO {
    private final String writer;
    private final String title;
    private final String contents;

    public Article toEntity() {
        return Article.builder()
                      .writer(writer)
                      .title(title)
                      .contents(contents)
                      .build();
    }
}
