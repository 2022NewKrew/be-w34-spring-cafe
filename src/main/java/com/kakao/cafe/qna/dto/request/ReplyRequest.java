package com.kakao.cafe.qna.dto.request;

import com.kakao.cafe.qna.domain.Reply;

public class ReplyRequest {

    private long qnaId;
    private String writer;
    private final String contents;

    public ReplyRequest(String contents) {
        this.contents = contents;
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

    public void setQnaId(long qnaId) {
        this.qnaId = qnaId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Reply toReply() {
        return new Reply(qnaId, writer, contents);
    }
}
