package com.kakao.cafe.dto.reply;

import com.kakao.cafe.domain.entity.Reply;

import java.time.LocalDateTime;

public class ReplyContents {
    private final long replyId;
    private final String writer;
    private final String writerId;
    private final String contents;
    private final LocalDateTime time;

    public ReplyContents(long replyId, String writer, String writerId, String contents, LocalDateTime time) {
        this.replyId = replyId;
        this.writer = writer;
        this.writerId = writerId;
        this.contents = contents;
        this.time = time;
    }

    public ReplyContents(ReplyWithWriterName replyWithWriterName) {
        this.replyId = replyWithWriterName.getReplyId();
        this.writer = replyWithWriterName.getWriter();
        this.writerId = replyWithWriterName.getWriterId();
        this.contents = replyWithWriterName.getContents();
        this.time = replyWithWriterName.getTime();
    }

    public long getReplyId() {
        return replyId;
    }

    public String getWriter() { return writer; }

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
