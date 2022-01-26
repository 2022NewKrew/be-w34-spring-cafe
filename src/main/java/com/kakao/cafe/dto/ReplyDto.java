package com.kakao.cafe.dto;

import com.kakao.cafe.domain.Reply;

public class ReplyDto {
    private Integer articleIndex;
    private String writer;
    private String content;
    private Integer replyIndex;

    public ReplyDto(int articleIndex, String writer, String content) {
        this.articleIndex = articleIndex;
        this.writer = writer;
        this.content = content;
    }

    public ReplyDto(int articleIndex, String writer, String content, int replyIndex) {
        this.articleIndex = articleIndex;
        this.writer = writer;
        this.content = content;
        this.replyIndex = replyIndex;
    }

    public int getArticleIndex() {
        return articleIndex;
    }

    public void setArticleIndex(int articleIndex) {
        this.articleIndex = articleIndex;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}


