package com.kakao.cafe.article;

public class ArticleRequest {

    private final long userSeq;

    private final String writer;

    private final String title;

    private final String contents;

    public ArticleRequest(long userSeq, String writer, String title, String contents) {
        this.userSeq = userSeq;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public long getUserSeq() {
        return userSeq;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

}
