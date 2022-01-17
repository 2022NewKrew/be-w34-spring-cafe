package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;
import lombok.Getter;

@Getter
public class ArticleResponseDto {
    private final String writer;
    private final String title;
    private final String contents;

    public ArticleResponseDto(Article article) {
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
    }
}
