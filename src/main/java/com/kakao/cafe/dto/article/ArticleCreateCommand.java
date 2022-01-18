package com.kakao.cafe.dto.article;

public class ArticleCreateCommand {
    private String writer;
    private String writerId;
    private String title;
    private String contents;

    public ArticleCreateCommand(String writer, String writerId, String title, String contents) {
        this.writer = writer;
        this.writerId = writerId;
        this.title = title;
        this.contents = contents;
    }

    public String getWriter() {
        return writer;
    }

    public String getWriterId() { return writerId; }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setWriterId(String writerId) { this.writerId = writerId; }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
