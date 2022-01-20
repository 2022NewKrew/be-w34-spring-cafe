package com.kakao.cafe.qna.domain;

import java.time.LocalDateTime;

public class Reply {

    private final long id;
    private final long qnaId;
    private final String writer;
    private final String contents;
    private final LocalDateTime createTime;

    public Reply(long qnaId, String writer, String contents) {
        this(0L, qnaId, writer, contents);
    }

    public Reply(long id, long qnaId, String writer, String contents) {
        this.id = id;
        this.qnaId = qnaId;
        this.writer = writer;
        this.contents = contents;
        this.createTime = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public long getQnaId() {
        return qnaId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }
}
