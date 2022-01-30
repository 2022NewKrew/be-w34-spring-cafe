package com.kakao.cafe.dto;

public class SampleReplyForm {

    private Long articleID;
    private String content;

    public SampleReplyForm(Long articleID, String content) {
        this.articleID = articleID;
        this.content = content;
    }

    public Long getArticleID() {
        return articleID;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "SampleReplyForm{" +
                "articleID=" + articleID +
                ", content='" + content + '\'' +
                '}';
    }
}
