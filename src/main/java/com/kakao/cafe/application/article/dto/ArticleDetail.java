package com.kakao.cafe.application.article.dto;

import com.kakao.cafe.application.reply.dto.Replies;
import com.kakao.cafe.application.user.dto.UserInfo;

public class ArticleDetail {

    private final int id;
    private final String userId;
    private final String writer;
    private final String title;
    private final String contents;
    private final String createdAt;
    private final Replies replies;
    private final int countOfReplies;

    public ArticleDetail(Builder builder) {
        this.id = builder.id;
        this.userId = builder.userId;
        this.writer = builder.writer;
        this.title = builder.title;
        this.contents = builder.contents;
        this.createdAt = builder.createdAt;
        this.replies = builder.replies;
        this.countOfReplies = builder.countOfReplies;
    }

    public int getCountOfReplies() {
        return countOfReplies;
    }

    public int getId() {
        return id;
    }

    public String getUserId() {
        return userId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public Replies getReplyList() {
        return replies;
    }

    public boolean isPossibleDeleteArticle(UserInfo sessionedUser) {
        return replies.isEmpty() || replies.containsReplyOf(sessionedUser.getUserId());
    }

    public static class Builder {

        private int id;
        private String userId;
        private String writer;
        private String title;
        private String contents;
        private String createdAt;
        private Replies replies;
        private int countOfReplies;

        public Builder id(int id) {
            this.id = id;
            return this;
        }

        public Builder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public Builder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }

        public Builder createdAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder replyList(Replies replies) {
            this.replies = replies;
            return this;
        }

        public Builder countOfReplies(int countOfReplies) {
            this.countOfReplies = countOfReplies;
            return this;
        }

        public ArticleDetail build() {
            return new ArticleDetail(this);
        }
    }
}
