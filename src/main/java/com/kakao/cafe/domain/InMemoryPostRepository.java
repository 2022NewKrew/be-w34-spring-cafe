package com.kakao.cafe.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPostRepository implements  PostRepository {

    private final List<Post> postList;
    private final AtomicInteger pk;

    public InMemoryPostRepository() {
        this.postList = Collections.synchronizedList(new ArrayList<>());
        pk = new AtomicInteger();
    }

    private int nextId() {
        return pk.incrementAndGet();
    }

    @Override
    public Post save(Post post) {
        post.setId(nextId());
        postList.add(post);
        return null;
    }

    @Override
    public List<Post> findAll() {
        return postList;
    }

    @Override
    public Post findByPostId(int id) {
        return postList.get(id);
    }
}
