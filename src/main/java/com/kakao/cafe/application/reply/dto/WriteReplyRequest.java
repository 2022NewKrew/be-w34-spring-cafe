package com.kakao.cafe.application.reply.dto;

public class WriteReplyRequest {

    private final int articleId;
    private final String userId;
    private final String writer;
    private final String contents;

    public WriteReplyRequest(Builder builder) {
        this.articleId = builder.articleId;
        this.userId = builder.userId;
        this.writer = builder.writer;
        this.contents = builder.contents;
    }

    public int getArticleId() {
        return articleId;
    }

    public String getUserId() {
        return userId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public static class Builder {

        private int articleId;
        private String userId;
        private String writer;
        private String contents;

        public WriteReplyRequest build() {
            return new WriteReplyRequest(this);
        }

        public Builder articleId(int articleId) {
            this.articleId = articleId;
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

        public Builder contents(String contents) {
            this.contents = contents;
            return this;
        }
    }
}
