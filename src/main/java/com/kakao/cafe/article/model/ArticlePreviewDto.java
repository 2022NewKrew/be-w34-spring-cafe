package com.kakao.cafe.article.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ArticlePreviewDto {
    private final Long id;
    private final String author;
    private final String title;
    private final String uploadTime;

    public static ArticlePreviewDto of(Article article){
        return ArticlePreviewDto.builder()
                .id(article.getId())
                .author(article.getAuthor())
                .title(article.getTitle())
                .uploadTime(article.getUploadTime())
                .build();
    }
}
