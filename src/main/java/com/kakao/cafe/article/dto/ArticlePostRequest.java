package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class ArticlePostRequest {

    @NotBlank
    @Size(min = 2)
    private final String title;

    @NotBlank
    @Size(min = 10)
    private final String body;

    public ArticlePostRequest(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public Article toEntity() {
        return Article.builder()
            .title(title)
            .body(body)
            .build();
    }
}
