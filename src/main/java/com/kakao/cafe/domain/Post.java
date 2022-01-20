package com.kakao.cafe.domain;

import java.time.LocalDate;

public class Post {
    private String title;
    private String writer;
    private LocalDate createddate;
    private int postId;
    private String content;
    private boolean isDeleted = false;

    public Post(){}

    public Post(String title, String writer, LocalDate createdDate, int ID, String content) {
        this.title = title;
        this.writer = writer;
        this.createddate = LocalDate.now();
        this.postId = ID;
        this.content = content;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateddate(LocalDate createddate) {
        this.createddate = createddate;
    }

    public int getPostId() {
        return postId;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }




    public void setTitle(String title) {
        this.title = title;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }



    public void setPostId(int postId) {
        this.postId = postId;
    }

    public LocalDate getCreateddate() {
        return createddate;
    }
}
