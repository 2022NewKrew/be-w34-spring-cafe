package com.kakao.cafe.domain;

import java.time.LocalDateTime;

public class Reply {
    private int id;
    private Post post;
    private Member writer;
    private String contents;
    private LocalDateTime createdAt;
    private boolean isRemoved;

    public Reply() {
    }

    public Reply(int id, Post post, Member writer, String contents, LocalDateTime createdAt, boolean isRemoved) {
        this.id = id;
        this.post = post;
        this.writer = writer;
        this.contents = contents;
        this.createdAt = createdAt;
        this.isRemoved = isRemoved;
    }

    public int getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public Member getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public void setWriter(Member writer) {
        this.writer = writer;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setRemoved(boolean removed) {
        isRemoved = removed;
    }
}
