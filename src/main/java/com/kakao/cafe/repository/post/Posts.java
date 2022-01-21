package com.kakao.cafe.repository.post;

import com.kakao.cafe.model.Post;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

class Posts {

    private final List<Post> posts;

    public Posts() {
        this.posts = Collections.synchronizedList(new ArrayList<>());
    }

    public void add(Post post) {
        posts.add(post);
    }

    public Optional<Post> findById(UUID id) {
        Optional<Post> result = posts.stream()
                .filter(post -> id.equals(post.getId()))
                .filter(post -> !post.isDeleted())
                .findFirst();

        return result.isPresent() ? Optional.of(Post.copy(result.get())) : Optional.empty();
    }

    public List<Post> getPosts() {
        return posts.stream()
                .filter(post -> !post.isDeleted())
                .map(Post::copy)
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
