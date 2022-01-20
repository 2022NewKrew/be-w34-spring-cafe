package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.Entity.Article;
import lombok.Getter;

@Getter
public class ReferArticleDto {
    private int articleId;
    private String writer;
    private String title;
    private String contents;

    public ReferArticleDto(Article article) {
        this.articleId = article.getArticleId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
    }
}
