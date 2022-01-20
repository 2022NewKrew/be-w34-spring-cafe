package com.kakao.cafe.domain.comment;

import java.util.List;

public class Comments {

    private List<Comment> comments;

    public Comments(List<Comment> comments) {
        this.comments = comments;
    }

    public int size() {
        return comments.size();
    }

    public List<Comment> getComments() {
        return comments;
    }
}
