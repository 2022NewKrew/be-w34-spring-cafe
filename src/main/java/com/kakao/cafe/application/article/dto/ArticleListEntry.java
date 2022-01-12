package com.kakao.cafe.application.article.dto;

public class ArticleListEntry {

    private final int index;
    private final String writer;
    private final String title;
    private final String postedDate;

    public ArticleListEntry(int index, String writer, String title, String postedDate) {
        this.index = index;
        this.writer = writer;
        this.title = title;
        this.postedDate = postedDate;
    }

    public int getIndex() {
        return index;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getPostedDate() {
        return postedDate;
    }
}
