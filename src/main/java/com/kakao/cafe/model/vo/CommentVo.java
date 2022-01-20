package com.kakao.cafe.model.vo;

public class CommentVo {

    private int id;
    private UserVo writer;
    private String contents;

    public CommentVo() {

    }

    public CommentVo(int id, UserVo writer, String contents) {
        this.id = id;
        this.writer = writer;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public UserVo getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }
}
