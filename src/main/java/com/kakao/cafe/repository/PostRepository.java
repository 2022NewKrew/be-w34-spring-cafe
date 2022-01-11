package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;

import java.util.List;
import java.util.Optional;


public interface PostRepository {
    void save(Post post);
    Optional<List<Post>> findAll();
}
