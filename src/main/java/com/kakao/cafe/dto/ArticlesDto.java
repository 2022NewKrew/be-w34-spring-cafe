package com.kakao.cafe.dto;

import java.util.List;
import java.util.stream.Collectors;

import com.kakao.cafe.domain.Article;

public class ArticlesDto {

    private final List<ArticleDto> articles;

    public ArticlesDto(List<Article> articles) {
        this.articles = articles.stream()
                                .map(ArticleDto::new)
                                .collect(Collectors.toList());
    }

    public List<ArticleDto> getArticles() {
        return this.articles;
    }
}

