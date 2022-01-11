package com.kakao.cafe.dto;

import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.User;

public class PostCreateRequest {

    private final String writer;
    private final String title;
    private final String content;

    public PostCreateRequest(String writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public Post toEntity(User writer) {
        return new Post.Builder(writer, title, content).build();
    }
}
