package com.kakao.cafe.article.dto.request;

import lombok.Builder;
import lombok.Getter;

import com.kakao.cafe.article.entity.Article;

@Builder
@Getter
public class ArticleUpdateRequestDTO {
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
