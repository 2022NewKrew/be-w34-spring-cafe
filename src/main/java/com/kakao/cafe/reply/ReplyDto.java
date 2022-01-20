package com.kakao.cafe.reply;

import java.time.LocalDateTime;

public class ReplyDto {

    private final long seq;

    private final long userSeq;

    private final long articleSeq;

    private final String writer;

    private final String content;

    private final LocalDateTime time;

    private final boolean owner;

    public ReplyDto(Reply reply) {
        this.seq = reply.getSeq();
        this.userSeq = reply.getUserSeq();
        this.articleSeq = reply.getArticleSeq();
        this.writer = reply.getWriter();
        this.content = reply.getContent();
        this.time = reply.getTime();
        this.owner = false;
    }

    public ReplyDto(Reply reply, long reqUserSeq) {
        this.seq = reply.getSeq();
        this.userSeq = reply.getUserSeq();
        this.articleSeq = reply.getArticleSeq();
        this.writer = reply.getWriter();
        this.content = reply.getContent();
        this.time = reply.getTime();
        this.owner = reqUserSeq == reply.getUserSeq();
    }

    public long getSeq() {
        return seq;
    }

    public long getUserSeq() {
        return userSeq;
    }

    public long getArticleSeq() {
        return articleSeq;
    }

    public String getWriter() {
        return writer;
    }


    public String getContents() {
        return content;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public boolean isOwner() {
        return owner;
    }

}
