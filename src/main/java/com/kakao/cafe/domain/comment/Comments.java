package com.kakao.cafe.domain.comment;

import java.util.List;
import java.util.stream.Collectors;

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

    public Comments filterExceptUserId(String userId) {
        return new Comments(
                comments.stream()
                        .filter(c -> !c.isUser(userId))
                        .collect(Collectors.toList()));
    }

}
