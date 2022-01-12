package com.kakao.cafe.vo;

public class Article {

    private Integer articleId;
    private String title;
    private String content;

    public Article(Integer articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
