package com.kakao.cafe.article.service;

import com.kakao.cafe.article.domain.Article;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class FindAllArticleResponseDTO {

    private ArrayList<OneArticleDataDTO> articles;

    public FindAllArticleResponseDTO(ArrayList<Article> articles) {
        this.articles = articles.stream().map(article->new OneArticleDataDTO(article.getId(), article.getTitle(), article.getAuthorId(), article.getDate().toString(), article.getHits())).collect(Collectors.toCollection(ArrayList::new));

    }

    @AllArgsConstructor
    public static class OneArticleDataDTO {
        public Long articleId;
        public String title;
        public Long authorId;
        public String signUpDate;
        public Integer hits;
    }
}
