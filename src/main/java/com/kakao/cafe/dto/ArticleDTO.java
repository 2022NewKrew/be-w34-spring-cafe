package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ArticleDTO {
    private String title;
    private String content;
    private Integer articleIndex;

    public static Article toEntity(ArticleDTO articleDTO){
        return Article.builder()
                .title(articleDTO.getTitle())
                .content(articleDTO.getContent())
                .build();
    }

    public static ArticleDTO toDto(Article article){
        return ArticleDTO.builder()
                .title(article.getTitle())
                .content(article.getContent())
                .articleIndex(article.getArticleIndex())
                .build();
    }
}
