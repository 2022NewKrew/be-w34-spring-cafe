package com.kakao.cafe.domain;

import com.kakao.cafe.dto.SampleReplyForm;

import javax.persistence.ManyToOne;

public class Reply {

    private Long replyID;
    private String author;
    private String content;

    private Long articleID;

    public Reply(Long articleID, String author, String content) {
        this.articleID = articleID;
        this.author = author;
        this.content = content;
    }

    public Long getReplyID() {
        return replyID;
    }

    public void setReplyID(Long replyID) {
        this.replyID = replyID;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    public static Reply add(SampleReplyForm form, String author){
        return new Reply(form.getArticleID(), author, form.getContent());
    }

}
