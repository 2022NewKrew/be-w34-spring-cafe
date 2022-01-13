package com.kakao.cafe.dto;

public class ArticleRegistrationDto {
    private Integer articleId;
    private String title;
    private String content;

    public Integer getArticleId() {
        return articleId;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
