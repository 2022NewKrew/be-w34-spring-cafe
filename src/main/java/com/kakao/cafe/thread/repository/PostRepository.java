package com.kakao.cafe.thread.repository;

import com.kakao.cafe.thread.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Long add(Post post);

    List<Post> getAll();

    Optional<Post> get(Long id);

    void update(Post post);
}
