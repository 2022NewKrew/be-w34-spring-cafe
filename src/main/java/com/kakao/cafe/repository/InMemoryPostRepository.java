package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;
import com.kakao.cafe.exceptions.PostNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPostRepository implements PostRepository {

    private final List<Post> postList;
    private final AtomicInteger pk;
    private final Logger logger = LoggerFactory.getLogger(InMemoryPostRepository.class);

    public InMemoryPostRepository() {
        this.postList = Collections.synchronizedList(new ArrayList<>());
        pk = new AtomicInteger();
    }

    private int nextId() {
        return pk.incrementAndGet();
    }

    @Override
    public Post save(Post post) {
        logger.debug("[InMemory] post save");
        Post savePost = new Post(nextId(), post.getUserId(), post.getTitle(), post.getContent(), new Date());
        postList.add(savePost);
        return savePost;
    }

    @Override
    public List<Post> findAll() {
        logger.debug("[InMemory] post findAll");
        return postList;
    }

    @Override
    public Post findByPostId(int id) {
        logger.debug("[InMemory] post findByPostId");
        if (id <= 0 || postList.size() < id) {
            throw new PostNotFoundException("없는 게시글 입니다");
        }
        return postList.get(id - 1);
    }

    @Override
    public void update(Post post) {
        postList.set(post.getId() - 1, post);
    }

    @Override
    public void delete(int id) {
        postList.remove(id - 1);
    }
}
