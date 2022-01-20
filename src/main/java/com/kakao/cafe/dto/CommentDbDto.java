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

    public CommentDbDto(Builder builder) {
        this(builder.getId(), builder.getPostId(), builder.getUserId(), builder.getText());
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

    public static class Builder {
        private long id = -1;
        private long postId;
        private String userId;
        private String text;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder postId(long postId) {
            this.postId = postId;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public CommentDbDto build() {
            if (userId == null)
                throw new IllegalArgumentException("userId is null");
            if (text == null)
                throw new IllegalArgumentException("postId is null");
            return new CommentDbDto(this);
        }

        public long getId() {
            return id;
        }

        public long getPostId() {
            return postId;
        }

        public String getUserId() {
            return userId;
        }

        public String getText() {
            return text;
        }
    }


}
