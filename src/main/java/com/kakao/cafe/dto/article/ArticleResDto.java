package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;
import lombok.Getter;

@Getter
public class ArticleResDto {
    private Long articleId;
    private String writer;
    private String title;
    private String contents;
    private Boolean deleted;

    public ArticleResDto(Article article){
        this.articleId = article.getArticleId();
        this.writer = article.getWriter();
        this.title = article.getTitle();
        this.contents = article.getContents();
        this.deleted = article.getDeleted();
    }
}
