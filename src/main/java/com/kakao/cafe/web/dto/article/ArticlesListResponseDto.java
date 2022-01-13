package com.kakao.cafe.web.dto.article;

import com.kakao.cafe.domain.article.Article;
import lombok.Getter;

import java.util.List;

@Getter
public class ArticlesListResponseDto {
    private final List<Article> articles;

    public ArticlesListResponseDto(List<Article> articles) {
        this.articles = articles;
    }
}
