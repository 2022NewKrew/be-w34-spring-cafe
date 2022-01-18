package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleResponseDto {

    private final Long id;
    private final String writer;
    private final String title;

    private ArticleResponseDto(Long id, String writer, String title) {
        this.id = id;
        this.writer = writer;
        this.title = title;
    }

    public static List<ArticleResponseDto> from(List<Article> articleList) {
        List<ArticleResponseDto> result = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResponseDto responseDto = new ArticleResponseDto(article.getId(), article.getWriter(), article.getTitle());
            result.add(responseDto);
        }
        return result;
    }
}
