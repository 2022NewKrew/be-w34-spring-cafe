package com.kakao.cafe.reply;

public class ReplyRequest {

    private final long userSeq;

    private final long articleSeq;

    private final String writer;

    private final String contents;

    public ReplyRequest(long userSeq, long articleSeq, String writer, String title, String contents) {
        this.userSeq = userSeq;
        this.articleSeq = articleSeq;
        this.writer = writer;
        this.contents = contents;
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
        return contents;
    }

}
