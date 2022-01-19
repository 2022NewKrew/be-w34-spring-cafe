package com.kakao.cafe.domain;

public class Qna {

    private Integer index;
    private String writer;
    private String title;
    private String contents;
    private Boolean deleted = false;

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

    public void updateQna(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public void delete() {
        this.deleted = true;
    }

    public Boolean isValidUpdateUser(String userId) {
        return writer.equals(userId);
    }

    public Boolean isValidDeleteUser(String userId) {
        return writer.equals(userId);
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }
}
