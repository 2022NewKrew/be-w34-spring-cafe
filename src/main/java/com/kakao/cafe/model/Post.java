package com.kakao.cafe.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Post {
    int postId;
    String userId; // 작성자 이름
    String title;
    String content;
    int view;
    String createdAt;

    public Post() {}
    public Post(int postId, String userId, String title, String content) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
    }
    public Post(int postId, String userId, String title, String content, int view, String createdAt) {
        this.postId = postId;
        this.userId = userId;
        this.title = title;
        this.content = content;
        this.view = view;
        this.createdAt = createdAt;
    }
}
