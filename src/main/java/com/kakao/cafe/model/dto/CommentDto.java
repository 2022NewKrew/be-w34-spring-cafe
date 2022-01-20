package com.kakao.cafe.model.dto;

import com.kakao.cafe.model.vo.UserVo;

public class CommentDto {

    private int id;
    private UserVo writer;
    private String contents;

    public CommentDto() {

    }

    public CommentDto(String contents) {
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

    public void setId(int id) {
        this.id = id;
    }

    public void setWriter(UserVo writer) {
        this.writer = writer;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
