package com.kakao.cafe.application.article.dto;

public class ArticleDetail {

    private final int index;
    private final String writer;
    private final String title;
    private final String contents;
    private final String postedDate;

    public ArticleDetail(int index, String writer, String title, String contents,
        String postedDate) {
        this.index = index;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.postedDate = postedDate;
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

    public String getPostedDate() {
        return postedDate;
    }

    public int getIndex() {
        return index;
    }
}
