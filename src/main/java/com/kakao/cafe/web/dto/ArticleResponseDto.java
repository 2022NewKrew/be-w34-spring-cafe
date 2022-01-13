package com.kakao.cafe.web.dto;

import com.kakao.cafe.domain.article.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleResponseDto {

    private Long id;
    private String writer;
    private String title;

    private ArticleResponseDto() {}

    public static List<ArticleResponseDto> from(List<Article> articleList) {
        List<ArticleResponseDto> result = new ArrayList<>();
        for (Article article : articleList) {
            ArticleResponseDto responseDto = new ArticleResponseDto();
            responseDto.setId(article.getId());
            responseDto.setWriter(article.getWriter());
            responseDto.setTitle(article.getTitle());
            result.add(responseDto);
        }
        return result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
