package com.kakao.cafe.repository;

import com.kakao.cafe.model.Post;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public class MemoryPostRepository implements PostRepository {

    private static final List<Post> postList = new ArrayList<>();

    @Override
    public Post save(Post post) {
        postList.add(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return postList;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return postList.stream()
                .filter(post -> id.equals(post.getId()))
                .findFirst();
    }
}
