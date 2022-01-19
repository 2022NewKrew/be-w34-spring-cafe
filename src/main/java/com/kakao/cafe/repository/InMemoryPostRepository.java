package com.kakao.cafe.repository;

import com.kakao.cafe.model.Post;
import com.kakao.cafe.model.Posts;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Repository;

@Repository
public class InMemoryPostRepository implements PostRepository {

    private final Posts posts;

    public InMemoryPostRepository() {
        this.posts = new Posts(Collections.synchronizedList(new ArrayList<>()));
    }

    @Override
    public void save(Post post) {
        posts.add(post);
    }

    @Override
    public List<Post> findAll() {
        return posts.getPosts();
    }

    @Override
    public Optional<Post> findById(UUID id) {
        return posts.findById(id);
    }

    @Override
    public void update(Post post) {
        posts.update(post);
    }
}
