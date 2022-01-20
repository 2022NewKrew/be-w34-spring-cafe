package com.kakao.cafe.domain;

public class Qna {

    private Integer id;
    private String writer;
    private String title;
    private String contents;
    private Boolean deleted;

    public Qna(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.deleted = false;
    }

    public Qna(Integer id, String writer, String title, String contents) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.deleted = false;
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

    public Integer getId() {
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

    public Boolean getDeleted() {
        return deleted;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
