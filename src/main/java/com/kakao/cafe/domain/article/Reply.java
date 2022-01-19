package com.kakao.cafe.domain.article;

public class Reply {

    private final Long id;
    private final Long articleId;
    private final Long authorId;
    private final String author;
    private final String description;
    private final boolean deleted;

    public Reply(Long articleId, Long authorId, String description) {
        this(null, articleId, authorId, null, description, false);
    }

    public Reply(Long id, Long articleId, Long authorId, String author, String description, boolean deleted) {
        validateDescription(description);
        this.id = id;
        this.articleId = articleId;
        this.authorId = authorId;
        this.author = author;
        this.description = description;
        this.deleted = deleted;
    }

    private void validateDescription(String description) {
        if (description.isBlank()) {
            throw new IllegalArgumentException("댓글의 내용을 반드시 입력해야 합니다.");
        }
    }

    public Long getId() {
        return id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
