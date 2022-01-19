package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ArticlePostRequest {

    @NotBlank
    @Size(min = 2, max = 20)
    private final String title;

    @NotBlank
    @Size(min = 10, max = 5000)
    private final String body;

    public Article toEntity(Long authorId) {
        return Article.builder()
            .title(title)
            .body(body)
            .authorId(authorId)
            .build();
    }
}
