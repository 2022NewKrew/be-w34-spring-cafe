package com.kakao.cafe.domain;

import com.kakao.cafe.exceptions.PostNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPostRepository implements  PostRepository {

    private final List<Post> postList;
    private final AtomicInteger pk;
    private Logger logger = LoggerFactory.getLogger(InMemoryPostRepository.class);

    public InMemoryPostRepository() {
        this.postList = Collections.synchronizedList(new ArrayList<>());
        pk = new AtomicInteger();
    }

    private int nextId() {
        return pk.incrementAndGet();
    }

    @Override
    public Post save(Post post) {
        logger.info("[InMemory] post save");
        post.setId(nextId());
        postList.add(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        logger.info("[InMemory] post findAll");
        return postList;
    }

    @Override
    public Post findByPostId(int id) {
        logger.info("[InMemory] post findByPostId");
        if (id <= 0 || postList.size() < id)
            throw new PostNotFoundException("없는 게시글 입니다");
        return postList.get(id - 1);
    }
}
