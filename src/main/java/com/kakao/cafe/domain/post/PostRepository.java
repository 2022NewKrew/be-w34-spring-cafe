package com.kakao.cafe.domain.post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post insert(Post post);

    List<Post> findAll(int pageNumber);

    Optional<Post> findById(Long id);

    Post update(Long id, Post post);

    void remove(Long id);

    int count();
}
