package com.kakao.cafe.replies;

public class ReplyCreationRequest {
    private String content;
    private Long articleId;
    private Long writerId;

    public String getContent() {
        return content;
    }

    public Long getArticleId() {
        return articleId;
    }

    public Long getWriterId() {
        return writerId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public void setWriterId(Long writerId) {
        this.writerId = writerId;
    }
}
