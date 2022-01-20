package com.kakao.cafe.application.reply.dto;

public class WriteReplyRequest {

    private final String contents;
    private int articleId;
    private String userId;
    private String writer;

    public WriteReplyRequest(String contents) {
        this.contents = contents;
    }

    public int getArticleId() {
        return articleId;
    }

    public void setArticleId(int articleId) {
        this.articleId = articleId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContents() {
        return contents;
    }
}
