package com.kakao.cafe.domain.article.dto;

import com.kakao.cafe.domain.article.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ArticleUpdateDto {
    private final Long id;
    private final Long userId;
    private final String title;
    private final String content;

    public Article toEntity() {
        return Article.builder()
                .id(id)
                .userId(userId)
                .title(title)
                .content(content)
                .build();
    }
}
