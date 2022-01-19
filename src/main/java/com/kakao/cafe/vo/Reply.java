package com.kakao.cafe.vo;

public class Reply {

    private String writer;
    private String contents;
    private int articleId;
    private int id;

    public Reply(String writer, String contents, int articleId) {
        this.writer = writer;
        this.contents = contents;
        this.articleId = articleId;
    }

    public Reply(String writer, String contents, int articleId, int id) {
        this.writer = writer;
        this.contents = contents;
        this.articleId = articleId;
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public int getArticleId() { return articleId; }

    public int getId() { return id; }

}
