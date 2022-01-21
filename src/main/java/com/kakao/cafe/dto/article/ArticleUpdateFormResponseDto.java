package com.kakao.cafe.dto.article;

public class ArticleUpdateFormResponseDto {

    private final String articleId;
    private final String title;
    private final String content;

    public ArticleUpdateFormResponseDto(String articleId, String title, String content) {
        this.articleId = articleId;
        this.title = title;
        this.content = content;
    }

    public String getArticleId() {
        return articleId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
