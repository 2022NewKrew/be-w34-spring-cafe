package com.kakao.cafe.model.vo;

public class ArticleVo {

    private final int id;
    private final String writer;
    private final String title;
    private final String contents;

    public ArticleVo(int id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
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
