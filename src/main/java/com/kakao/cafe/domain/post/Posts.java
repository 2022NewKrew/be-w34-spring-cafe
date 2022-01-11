package com.kakao.cafe.domain.post;

import java.util.ArrayList;
import java.util.List;

public class Posts {

    private final List<Post> posts;

    public Posts() {
        posts = new ArrayList<>();
    }

    public Posts(List<Post> posts) {
        this.posts = posts;
    }

    public int size() {
        return posts.size();
    }

    public List<Post> getPosts() {
        return posts;
    }
}
