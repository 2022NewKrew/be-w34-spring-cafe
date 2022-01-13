package com.kakao.cafe.domain.post;

import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryPostRepository implements PostRepository {
    private final Map<Long, Post> postStore = new HashMap<>();

    @Override
    public void save(Post post) {
        long insertId = postStore.size() + 1;
        post.setId(insertId);

        postStore.put(insertId, post);
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(postStore.values());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(postStore.get(id));
    }
}
