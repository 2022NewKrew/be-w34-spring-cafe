package com.kakao.cafe.domain;

public class Article {
    private String title;
    private String content;
    private Integer articleIndex;

    public Article(String title, String content, Integer articleIndex) {
        this.title = title;
        this.content = content;
        this.articleIndex = articleIndex;
    }

    public Article(Article article) {
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

    public Integer getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(Integer articleIndex) {
        this.articleIndex = articleIndex;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

