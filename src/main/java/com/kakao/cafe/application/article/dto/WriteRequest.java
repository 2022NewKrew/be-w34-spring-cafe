package com.kakao.cafe.application.article.dto;

public class WriteRequest {

    private final String userId;
    private final String writer;
    private final String title;
    private final String contents;

    public WriteRequest(Builder builder) {
        this.userId = builder.userId;
        this.writer = builder.writer;
        this.title = builder.title;
        this.contents = builder.contents;
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

    public static class Builder {

        private String userId;
        private String writer;
        private String title;
        private String contents;

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

        public WriteRequest build() {
            return new WriteRequest(this);
        }
    }
}
