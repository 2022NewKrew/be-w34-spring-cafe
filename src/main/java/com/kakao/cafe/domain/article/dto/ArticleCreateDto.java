package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter @Setter
public class ArticleCreateDto {
    private Long userId;
    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
