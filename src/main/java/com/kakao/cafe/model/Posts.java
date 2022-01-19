package com.kakao.cafe.model;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Posts {

    private final List<Post> posts;

    public Posts(List<Post> posts) {
        this.posts = posts;
    }

    public void add(Post post) {
        posts.add(post);
    }

    public Optional<Post> findById(UUID id) {
        return posts.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst();
    }

    public List<Post> getPosts() {
        return Collections.unmodifiableList(posts);
    }

    public void update(Post modified) {
        posts.stream()
                .filter((original) -> original.getId().equals(modified.getId()))
                .forEach((original) -> original.update(modified));
    }
}
