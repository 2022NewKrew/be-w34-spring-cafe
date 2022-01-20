package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Article;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ArticleResponseDto {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private final Long id;
    private final String writer;
    private final String title;
    private final String createdAt;

    private ArticleResponseDto(Long id, String writer, String title, LocalDateTime createdAt) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.createdAt = createdAt.format(FORMATTER);
    }

    public static List<ArticleResponseDto> from(List<Article> articleList) {
        List<ArticleResponseDto> result = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResponseDto responseDto = new ArticleResponseDto(article.getId(), article.getWriter(), article.getTitle(), article.getCreatedAt());
            result.add(responseDto);
        }
        return result;
    }
}
