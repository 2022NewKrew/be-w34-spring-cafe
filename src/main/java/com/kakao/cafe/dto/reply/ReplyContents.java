package com.kakao.cafe.dto.reply;

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
