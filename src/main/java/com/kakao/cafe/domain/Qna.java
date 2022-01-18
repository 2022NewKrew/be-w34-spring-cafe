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

    public Qna(Integer index, String writer, String title, String contents) {
        this.index = index;
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

    public Boolean isValidUpdateUser(String userId) {
        return writer.equals(userId);
    }

    public void updateQna(Qna qna) {
        this.title = qna.getTitle();
        this.contents = qna.getContents();
    }
}
