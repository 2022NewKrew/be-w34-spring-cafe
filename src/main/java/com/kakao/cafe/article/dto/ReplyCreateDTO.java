package com.kakao.cafe.article.dto;

public class ReplyCreateDTO {
    private String userId;
    private Long articleSeq;
    private String contents;

    public ReplyCreateDTO(String userId, Long articleSeq, String contents) {
        this.userId = userId;
        this.articleSeq = articleSeq;
        this.contents = contents;
    }

    public String getUserId() {
        return userId;
    }

    public Long getArticleSeq() {
        return articleSeq;
    }

    public String getContents() {
        return contents;
    }
}
