package com.kakao.cafe.domain;

import java.util.Date;

public class Reply {

    private final int id;
    private final int userId;
    private final int postId;
    private final String comment;
    private final Date createdAt;

    public Reply(int id, int userId, int postId, String comment, Date createdAt) {
        this.id = id;
        this.userId = userId;
        this.postId = postId;
        this.comment = comment;
        this.createdAt = createdAt;
    }

    public boolean isUser(int userId) {
        return this.userId == userId;
    }

    public String getComment() {
        return comment;
    }

    public int getUserId() {
        return userId;
    }

    public int getPostId() {
        return postId;
    }

    @Override
    public String toString() {
        return "Reply id: " + id +
                ", userId: " + userId +
                ", postId: " + postId +
                ", comment: " + comment +
                ", createdAt: " + createdAt;
    }
}
