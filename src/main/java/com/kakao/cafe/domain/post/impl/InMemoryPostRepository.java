package com.kakao.cafe.domain.post.impl;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.domain.post.PostRepository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class InMemoryPostRepository implements PostRepository {
    private static final List<Post> store = Collections.synchronizedList(new ArrayList<>());
    private static final AtomicLong sequence = new AtomicLong(1L);

    @Override
    public void add(Post post) {
        post.setId(sequence.getAndIncrement());
        store.add(post);
    }

    @Override
    public Optional<Post> findById(long id) {
        return Optional.ofNullable(store.get((int)id - 1));
    }

    @Override
    public List<Post> findAllByWriter(String writer) {
        return store.stream()
                .filter(post -> post.getWriter().equals(writer))
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> findAll() {
        return store;
    }
}
