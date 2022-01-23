package com.kakao.cafe.domain;

import java.time.LocalDate;

public class Reply {
    private int id;
    private String writer;
    private String content;
    private int postid;
    private int userid;
    private LocalDate createddate;

    public Reply(){}

    public Reply(int id, String writer, String content, int postid, int userid, LocalDate createddate) {
        this.id = id;
        this.writer = writer;
        this.content = content;
        this.postid = postid;
        this.userid = userid;
        this.createddate=LocalDate.now();
    }

    public int getPostid() {
        return postid;
    }

    public void setPostid(int postid) {
        this.postid = postid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public LocalDate getCreateddate() {
        return createddate;
    }

    public void setCreateddate(LocalDate createddate) {
        this.createddate = createddate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
