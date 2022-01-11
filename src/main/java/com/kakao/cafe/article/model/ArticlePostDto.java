package com.kakao.cafe.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ArticlePostDto {
    private final Long id;
    private final String author;
    private final String title;
    private final String contents;
    private final String uploadTime;

    public static ArticlePostDto of(Article article){
        return ArticlePostDto.builder()
                .id(article.getId())
                .author(article.getAuthor())
                .title(article.getTitle())
                .contents(article.getContents())
                .uploadTime(article.getUploadTime())
                .build();
    }
}
