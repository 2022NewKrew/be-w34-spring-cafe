package com.kakao.cafe.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
                .filter(post -> !post.isDeleted())
                .findFirst();
    }

    public List<Post> getPosts() {
        return posts.stream()
                .filter(post -> !post.isDeleted())
                .collect(Collectors.toUnmodifiableList());
    }

    public void update(Post modified) {
        posts.stream()
                .filter((original) -> original.getId().equals(modified.getId()))
                .forEach((original) -> original.update(modified));
    }

    public void delete(Post post) {
        post.delete();
    }
}
