package com.kakao.cafe.model;

public class Post {
    int postId;
    String userId; // 작성자 이름
    String title;
    String content;
    String createdAt;

    public Post(String userId, String title, String content) {
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    public Post(int postId, String userId, String title, String content, String createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getPostId() {
        return postId;
    }
    public void setPostId(int postId) {
        this.postId = postId;
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
    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
