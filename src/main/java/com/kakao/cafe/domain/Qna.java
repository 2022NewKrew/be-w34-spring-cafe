package com.kakao.cafe.domain;

public class Qna {

    private Integer index;
    private String writer;
    private String title;
    private String contents;

    public Qna(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
    }

    public Integer getIndex() {
        return index;
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

    public void setIndex(Integer index) {
        this.index = index;
    }
}
