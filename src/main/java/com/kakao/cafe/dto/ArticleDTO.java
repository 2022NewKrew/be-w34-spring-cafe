package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Article;

public class ArticleDTO {
    private String title;
    private String content;
    private Integer articleIndex;

    public ArticleDTO() {
    }

    public ArticleDTO(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
        this.articleIndex = article.getArticleIndex();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(Integer articleIndex) {
        this.articleIndex = articleIndex;
    }
}
