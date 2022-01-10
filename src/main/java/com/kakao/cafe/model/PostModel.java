package com.kakao.cafe.model;

import com.kakao.cafe.domain.post.Post;

public class PostModel {

    private static int idxCursor = 1;

    private int idx;
    private String writer;
    private String title;
    private String contents;

    public PostModel(){

    }

    public PostModel(String writer, String title, String contents){
        this.writer = writer;
        this.title = title;
        this.contents = contents;

        this.idx = idxCursor++;
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

    public int getIdx() {
        return idx;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public static PostModel fromPost(Post post){
        return new PostModel(post.getWriter().getId(),
                post.getTitle(),
                post.getContents());
    }


    @Override
    public String toString() {
        return "PostModel{" +
                "writer='" + writer + '\'' +
                ", title='" + title + '\'' +
                ", contents='" + contents + '\'' +
                '}';
    }
}
