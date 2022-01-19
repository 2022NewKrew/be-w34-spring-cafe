package com.kakao.cafe.dto.article;

import com.kakao.cafe.domain.article.Article;

import java.util.List;

public class ArticleDetailDto {

    private final Long articleId;
    private final Long authorId;
    private final String author;
    private final String title;
    private final String description;
    private final List<ReplyDto> replies;

    public ArticleDetailDto(Article article, List<ReplyDto> replies) {
        this.articleId = article.getId();
        this.authorId = article.getAuthorId();
        this.author = article.getAuthor();
        this.title = article.getTitle();
        this.description = article.getDescription();
        this.replies = replies;
    }
}
