package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;

import java.util.List;
import java.util.Optional;


public interface PostRepository {
    Optional<List<Post>> findAll();

    Optional<Post> findById(int questionId);

    void remove(Post post);

    int save(Post post);

    void update(Post post);
}
