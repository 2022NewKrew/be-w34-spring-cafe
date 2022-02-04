package com.kakao.cafe.dto;

public class ReplyDeleteAjaxForm {
    private Long articleID;
    private Long replyID;

    public ReplyDeleteAjaxForm(Long articleID, Long replyID) {
        this.articleID = articleID;
        this.replyID = replyID;
    }

    public Long getArticleID() {
        return articleID;
    }

    public void setArticleID(Long articleID) {
        this.articleID = articleID;
    }

    public Long getReplyID() {
        return replyID;
    }

    public void setReplyID(Long replyID) {
        this.replyID = replyID;
    }
}
