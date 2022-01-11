package com.kakao.cafe.controller.dto;

import com.kakao.cafe.domain.Article;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDto {
    private String writer;
    private String title;
    private String content;

    public ArticleDto() {}

    public static ArticleDto from(Article article) {
        ArticleDto articleDto = new ArticleDto();
        articleDto.setTitle(article.getTitle());
        articleDto.setWriter(article.getWriter());
        articleDto.setContent(article.getContent());
        return articleDto;
    }
}
