package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.entity.Reply;

import java.time.LocalDateTime;

public class ReplyContents {
    private final long replyId;
    private final String writerId;
    private final String contents;
    private final LocalDateTime time;

    public ReplyContents(long replyId, String writerId, String contents, LocalDateTime time) {
        this.replyId = replyId;
        this.writerId = writerId;
        this.contents = contents;
        this.time = time;
    }

    public ReplyContents(Reply reply) {
        this.replyId = reply.getReplyId();
        this.writerId = reply.getWriterId();
        this.contents = reply.getContents();
        this.time = reply.getTime();
    }

    public long getReplyId() {
        return replyId;
    }

    public String getWriterId() {
        return writerId;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
