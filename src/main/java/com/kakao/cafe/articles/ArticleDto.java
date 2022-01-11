package com.kakao.cafe.articles;

public class ArticleDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;

    public ArticleDto(Long id, String title, String content, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public static ArticleDto toDto(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent().getBody(), article.getWriter());
    }
}
