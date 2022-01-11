package com.kakao.cafe.article.dto;

import com.kakao.cafe.article.domain.Article;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
public class ArticleRequestDTO {

    private String title;
    private String author;
    private String content;

    public Article toArticle(LocalDate createDate) {
        return Article.builder()
                .title(title)
                .author(author)
                .content(content)
                .createDate(createDate)
                .build();
    }
}
