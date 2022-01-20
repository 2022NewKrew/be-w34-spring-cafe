package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;

import java.util.List;
import java.util.stream.Collectors;

public class ArticlesDTO {

    private final List<ArticleDTO> articles;

    public ArticlesDTO(List<Article> articles) {
        this.articles = articles.stream()
                                .map(ArticleDTO::toDto)
                                .collect(Collectors.toList());
    }

    public List<ArticleDTO> getArticles() {
        return this.articles;
    }
}

