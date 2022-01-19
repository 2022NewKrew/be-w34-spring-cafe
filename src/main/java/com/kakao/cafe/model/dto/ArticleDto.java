package com.kakao.cafe.model.dto;

import com.kakao.cafe.model.vo.UserVo;

public class ArticleDto {

    private int id;
    private UserVo writer;
    private String title;
    private String contents;

    public ArticleDto() {

    }

    public ArticleDto(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }

    public int getId() {
        return id;
    }

    public UserVo getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
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

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
