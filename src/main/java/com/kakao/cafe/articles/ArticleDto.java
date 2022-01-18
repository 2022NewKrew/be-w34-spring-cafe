package com.kakao.cafe.articles;

public class ArticleDto {
    private final Long id;
    private final String title;
    private final String content;
    private final String writer;
    private final Long writerId;

    public ArticleDto(Long id, String title, String content, String writer, Long writerId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.writerId = writerId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public Long getWriterId() {
        return writerId;
    }

    public static ArticleDto toDto(Article article) {
        return new ArticleDto(article.getId(), article.getTitle(), article.getContent().getBody(), article.getWriter(), article.getWriterId());
    }
}
