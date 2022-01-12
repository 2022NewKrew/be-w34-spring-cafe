package com.kakao.cafe.interfaces.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.interfaces.article.dto.response.ArticleListResponseDto;
import com.kakao.cafe.interfaces.article.dto.response.ArticleResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleMapper {
    private ArticleMapper() {
    }

    public static List<ArticleListResponseDto> convertEntityListToResponseDtoList(List<Article> articleList) {
        return articleList.stream()
                .map(e -> new ArticleListResponseDto(e.getId(), e.getWriter(), e.getCreatedAt(), e.getTitle()))
                .collect(Collectors.toList());
    }

    public static ArticleResponseDto convertEntityToResponseDto(Article article) {
        return new ArticleResponseDto(article.getId(),
                article.getWriter(),
                article.getCreatedAt(),
                article.getTitle(),
                article.getContents());
    }
}
