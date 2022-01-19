package com.kakao.cafe.domain.article.reply;

import java.time.LocalDateTime;

public class Reply {
    private Long id;
    private Long articleId;
    private String author;
    private String comment;
    private Boolean isDeleted;
    private LocalDateTime createdAt;

    public Reply(Long articleId, String author, String comment, LocalDateTime createdAt) {
        this.articleId = articleId;
        this.author = author;
        this.comment = comment;
        this.createdAt = createdAt;
        this.isDeleted = false;
    }

    public Reply(Long articleId, String author, String comment) {
        this(articleId, author, comment, LocalDateTime.now());
    }

    public boolean isReplyAuthor(String userId) {
        return author.equals(userId);
    }

    public void checkAuthor(String currentUserId) {
        if(!isReplyAuthor(currentUserId)) {
            throw new IllegalArgumentException("사용자는 해당 댓글의 글쓴이가 아닙니다.");
        }
    }

    public void delete(String userId) {
        if(!isReplyAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 해당 댓글을 삭제할 수 있는 권한이 없습니다.");
        }
        this.isDeleted = true;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public String getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getComment() {
        return comment;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }
}
