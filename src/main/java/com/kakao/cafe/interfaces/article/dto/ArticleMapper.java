package com.kakao.cafe.interfaces.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.interfaces.article.dto.response.ArticleResponseDto;

import java.util.List;
import java.util.stream.Collectors;

public class ArticleMapper {
    private ArticleMapper() {
    }

    public static List<ArticleResponseDto> convertEntityListToResponseDtoList(List<Article> articleList) {
        return articleList.stream()
                .map(e -> new ArticleResponseDto(e.getIndex(), e.getWriter(), e.getCreatedAt(), e.getTitle()))
                .collect(Collectors.toList());
    }
}
