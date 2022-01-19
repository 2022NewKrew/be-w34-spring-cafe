package com.kakao.cafe.domain.article;

import com.kakao.cafe.domain.article.reply.Reply;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Article {
    private Long id;
    private String author;
    private String title;
    private String content;
    private List<Reply> replies;
    private Boolean isDeleted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Article(String author, String title, String content) {
        this(author, title, content, LocalDateTime.now(), LocalDateTime.now());
    }

    public Article(String author, String title, String content, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.author = author;
        this.title = title;
        this.content = content;
        this.replies = new ArrayList<>();
        this.isDeleted = false;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update(String title, String content, String userId) {
        if(!isAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 해당 게시글을 수정할 수 있는 권한이 없습니다.");
        }
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

    public void delete(String userId) {
        if (!isAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 해당 게시글을 삭제할 수 있는 권한이 없습니다.");
        }
        boolean existUnableDeleteReply = replies.stream().anyMatch(reply -> !reply.isReplyAuthor(userId));
        if (existUnableDeleteReply) {
            throw new IllegalArgumentException("사용자는 해당 게시글에 포함된 댓글까지 삭제할 수 있는 권한이 없습니다.");
        }
        replies.forEach(reply -> reply.delete(userId));
        this.isDeleted = true;
        this.updatedAt = LocalDateTime.now();
    }

    public boolean isAuthor(String userId) {
        return author.equals(userId);
    }

    public void checkAuthor(String userId) {
        if(!isAuthor(userId)) {
            throw new IllegalArgumentException("사용자는 해당 게시글의 글쓴이가 아닙니다.");
        }
    }

    public void checkIncludeReply(Reply reply) {
        if(!id.equals(reply.getArticleId())) {
            throw new IllegalArgumentException("해당 게시글의 댓글이 아닙니다.");
        }
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setReplies(List<Reply> replies) {
        this.replies = replies;
    }

    public Long getId() {
        return id;
    }

    public List<Reply> getReplies() {
        return replies;
    }

    public String getAuthor() {
        return author;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }
}
