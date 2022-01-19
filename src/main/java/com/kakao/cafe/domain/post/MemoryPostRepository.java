package com.kakao.cafe.domain.post;

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

    @Override
    public Post edit(Long id, Post post) {
        postStore.put(id, post);

        return post;
    }

    @Override
    public void remove(Long id) {
        postStore.remove(id);
    }

    public void clear() {
        postStore.clear();
    }
}
