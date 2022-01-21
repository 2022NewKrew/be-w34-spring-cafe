package com.kakao.cafe.domain.comment;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.user.User;

public class Comment {

    private final long id;
    private final Post post;
    private final User user;
    private final Text text;

    private Comment(long id, Post post, User user, String text) {
        this.id = id;
        this.post = post;
        this.user = user;
        this.text = new Text(text);
    }

    public Comment(Builder builder) {
        this(builder.getId(), builder.getPost(), builder.getUser(), builder.getText());
    }

    public boolean isUser(String userId) {
        return user.isUserId(userId);
    }

    public long getId() {
        return id;
    }

    public Post getPost() {
        return post;
    }

    public User getUser() {
        return user;
    }

    public String getText() {
        return text.getText();
    }

    public static class Builder {
        private long id = -1;
        private Post post = null;
        private User user = null;
        private String text;

        public Builder id(long id) {
            this.id = id;
            return this;
        }

        public Builder post(Post post) {
            this.post = post;
            return this;
        }

        public Builder user(User user) {
            this.user = user;
            return this;
        }

        public Builder text(String text) {
            this.text = text;
            return this;
        }

        public Comment build() {
            return new Comment(this);
        }

        public long getId() {
            return id;
        }

        public Post getPost() {
            return post;
        }

        public User getUser() {
            return user;
        }

        public String getText() {
            return text;
        }
    }


}
