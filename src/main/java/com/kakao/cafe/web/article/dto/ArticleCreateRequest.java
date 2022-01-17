package com.kakao.cafe.web.article.dto;

import com.kakao.cafe.domain.article.Article;
import com.kakao.cafe.domain.article.Content;
import com.kakao.cafe.domain.article.Title;

public class ArticleCreateRequest {
    private final Title title;
    private final Content content;

    public ArticleCreateRequest(Title title, Content content) {
        this.title = title;
        this.content = content;
    }

    public Article toEntity() {
        Article article = new Article();
        article.setTitle(title);
        article.setContent(content);

        return article;
    }
}
