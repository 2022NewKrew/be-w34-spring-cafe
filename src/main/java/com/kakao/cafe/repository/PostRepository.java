package com.kakao.cafe.repository;

import com.kakao.cafe.domain.Post;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;


public interface PostRepository {
    int save(Post post);
    Optional<List<Post>> findAll();
    Optional<Post> findById(int questionId);
    void update(Post post);
}
