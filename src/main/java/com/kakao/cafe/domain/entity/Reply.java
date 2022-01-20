package com.kakao.cafe.domain.entity;

import java.time.LocalDateTime;

public class Reply {
    private final long replyId;
    private final long articleId;
    private final String writerId;
    private final String contents;
    private final LocalDateTime time;

    public Reply(long replyId, long articleId, String writerId, String contents, LocalDateTime time) {
        this.replyId = replyId;
        this.articleId = articleId;
        this.writerId = writerId;
        this.contents = contents;
        this.time = time;
    }

    public long getReplyId() {
        return replyId;
    }

    public long getArticleId() {
        return articleId;
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
