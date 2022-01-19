package com.kakao.cafe.dto;

public class CommentDbDto {
    private long id;
    private long postId;
    private String userId;
    private String text;

    public CommentDbDto(long id, long postId, String userId, String text) {
        this.id = id;
        this.postId = postId;
        this.userId = userId;
        this.text = text;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
