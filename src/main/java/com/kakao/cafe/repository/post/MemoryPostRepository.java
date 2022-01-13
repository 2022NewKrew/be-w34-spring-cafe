package com.kakao.cafe.repository.post;

import com.kakao.cafe.domain.post.Post;
import com.kakao.cafe.dto.post.PostResDto;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryPostRepository implements PostRepository {

    private static final Map<Long, Post> postStore = new HashMap<>();


    @Override
    public void save(Post post) {
        postStore.put(post.getPostId(), post);
    }

    @Override
    public Optional<Post> findByPostId(Long id) {
        return Optional.ofNullable(postStore.get(id));
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(postStore.values());
    }
}
